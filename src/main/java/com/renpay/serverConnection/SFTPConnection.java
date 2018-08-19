package com.renpay.serverConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.renpay.utils.TestInitialization;

public class SFTPConnection {

	private String serverIP = null;
	private String serverUser = null;
	private String serverPassword = null;
	private String jsFilePathOnServer = null;
	private String originalFilePath = null;

	public SFTPConnection() {
		this.serverIP = TestInitialization.getBuildParam("Server_IP");
		this.serverUser = TestInitialization.getBuildParam("Server_UserName");
		this.serverPassword = TestInitialization.getBuildParam("Server_Password");
		this.jsFilePathOnServer = TestInitialization.getBuildParam("BEP_GUI_PACKAGE_FILE_PATH");
	}

	public void removeSubscriberTab() throws FileNotFoundException, JSchException, SftpException, InterruptedException {

		String downloadedFilePathOnLocal = saveJSFileToLocal(this.jsFilePathOnServer);
		this.originalFilePath = downloadedFilePathOnLocal;
		String updatedFile = removeSubscriberEntryFromFile(downloadedFilePathOnLocal);
		UploadFileOnServer(updatedFile, new File(this.jsFilePathOnServer).getName());
	}

	public void addSubscriberTab() throws FileNotFoundException, JSchException, SftpException, InterruptedException {
		UploadFileOnServer(this.originalFilePath, new File(this.jsFilePathOnServer).getName());	
	}

	private String removeSubscriberEntryFromFile(String filePath) {

		Scanner sc = null;
		PrintWriter printer = null;
		String updatedFilePath = System.getProperty("user.dir") + File.separator + "UpdatedFile.js";

		try {
			File input = new File(filePath);
			File output = new File(updatedFilePath);
			sc = new Scanner(input);
			printer = new PrintWriter(output);
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				s = s.replace("{id:\"1001\",name:\"Subscriber\"},", "");
				printer.write(s);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Please scan in new file.");
		} finally {
			sc.close();
			printer.close();
		}
		return updatedFilePath;
	}

	private void UploadFileOnServer(String path, String fileNameToCreatedOnServer)
			throws JSchException, FileNotFoundException, SftpException, InterruptedException {
		Session session = null;
		ChannelSftp channelSftp = null;
		Channel channel = null;
		String basePath = FilenameUtils.getPath(this.jsFilePathOnServer);

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(serverUser, serverIP, 22);
			session.setPassword(serverPassword);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("For uploading file. HOST conneted");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("sftp channel opened and connected.");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd("/" + basePath);
			File f = new File(path);
			System.out.println("Uploading file name " + f.getName());
			channelSftp.put(new FileInputStream(f), fileNameToCreatedOnServer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.disconnect();
				channel.disconnect();
				channelSftp.quit();

			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	}

	private String saveJSFileToLocal(String filePathOnServer) {

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		String localFilePath = System.getProperty("user.dir") + File.separator + "OrignalFile.js";
		System.out.println("File path on server:" + filePathOnServer);

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(serverUser, serverIP, 22);
			session.setPassword(serverPassword);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println("For downloading file. HOST conneted");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("sftp channel opened and connected.");
			channelSftp = (ChannelSftp) channel;

			OutputStream output = new FileOutputStream(localFilePath);
			channelSftp.get(filePathOnServer, output);
			System.out.println("File downloaded : " + localFilePath);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (channelSftp.isConnected()) {
				try {
					session.disconnect();
					channel.disconnect();
					channelSftp.quit();

				} catch (Exception ioe) {
					ioe.printStackTrace();
				}
			}

		}

		return localFilePath;
	}
	
	
}
