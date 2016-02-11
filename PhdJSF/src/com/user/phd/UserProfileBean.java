package com.user.phd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.facade.UserFacade;
import com.model.User;

@RequestScoped
@ManagedBean(name = "userProfile", eager = true)
public class UserProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// TO BE EXPORTED TO CONFIGURATION FILE
	private static final String uploadsDirectory = "/usr/local/share/jboss/standalone/deployments/PhdEAR.ear/PhdJSF.war/resources/uploads/";

	@EJB
	private UserFacade userFacade;
	private User user;

	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	// Upload file
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			try {
				File folder = new File(uploadsDirectory);
				String filename = "profilePhoto";
				String extension = FilenameUtils.getExtension(file
						.getFileName());
				File fileUpload = File.createTempFile(filename,
						"." + extension, folder);
				String fileUploadPath = "/resources/images/userPhoto.png";
				if (fileUpload.getName() != null) {
					fileUploadPath = fileUpload.getName();
				}
				String oldProfilePhotoPath = uploadsDirectory
						+ getUser().getProfilePhoto();
				File oldProfilePhoto = new File(oldProfilePhotoPath);
				oldProfilePhoto.delete();
				getUser().setProfilePhoto(fileUploadPath);
				updateUser();

				InputStream input = file.getInputstream();
				byte[] bytes = IOUtils.toByteArray(input);
				InputStream inputTest = new ByteArrayInputStream(bytes);
				OutputStream output = new FileOutputStream(fileUpload, true);
				IOUtils.copy(inputTest, output);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("successfullPhotoUpload"), null));
			} catch (Exception e) {
				System.err.print("Profile picture upload, create new file: "
						+ e.getMessage());
			}
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// Upload file ends

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String username = context.getUserPrincipal().getName();
			user = userFacade.findByUsername(username);
		}
		return user;
	}

	public void updateUser() {
		try {
			userFacade.update(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String confirmChangePassword() {
		try {
			if (!oldPassword.equals(getUser().getProfilePassword())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("changePasswordWrongOldPassword"),
								null));
				return null;
			} else {
				if (!newPassword.equals(newPasswordConfirm)) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											bundle.getString("changePasswordDoNotMatch"),
											null));
					return null;
				}
				user = getUser();
				user.setProfilePassword(newPassword);
				updateUser();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("changePasswordSuccess"), null));
				return null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}