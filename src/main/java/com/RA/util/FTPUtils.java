package com.RA.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtils {

	private String server = "demo.wftpserver.com";
	private int port = 21;
	private String user = "demo";
	private String password = "demo";
	private FTPClient ftp;

	public void connect() {

		ftp = new FTPClient();

		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		try {
			ftp.connect(server, port);
			ftp.setKeepAlive(true);
			ftp.setDataTimeout(60000);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new IOException("Exception in connecting to FTP Server");
			}
			ftp.enterLocalPassiveMode();
			ftp.login(user, password);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> listDir(String path) throws IOException {
		FTPFile[] files = ftp.listFiles(path);
		Arrays.sort(files,
			    Comparator.comparing((FTPFile remoteFile) -> remoteFile.getTimestamp()).reversed());
		return Arrays.stream(files).map(FTPFile::getName).collect(Collectors.toList());
	}
	
	public InputStream readFile(String path) throws IOException {
		return ftp.retrieveFileStream(path);
	}
	
	public void changeDirectory(String path) throws IOException {
		
		ftp.changeWorkingDirectory(path);
	}

	public void close() throws IOException {
		ftp.disconnect();
	}
}
