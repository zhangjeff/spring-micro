package com.jeff.util;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;


public class FileSystemResource implements Resource {

	private final File file;

	private final String path;



	public FileSystemResource(File file) {
		this.file = file;
		this.path = StringUtils.cleanPath(file.getPath());
	}

	public FileSystemResource(String path) {
		this.file = new File(path);
		this.path = StringUtils.cleanPath(path);
	}


	/**
	 * Return the file path for this resource.
	 */
	public final String getPath() {
		return this.path;
	}


	/**
	 * This implementation returns whether the underlying file exists.
	 * @see java.io.File#exists()
	 */
	public boolean exists() {
		return this.file.exists();
	}

	/**
	 * This implementation checks whether the underlying file is marked as readable
	 * (and corresponds to an actual file with content, not to a directory).
	 * @see java.io.File#canRead()
	 * @see java.io.File#isDirectory()
	 */
	public boolean isReadable() {
		return (this.file.canRead() && !this.file.isDirectory());
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	/**
	 * This implementation opens a FileInputStream for the underlying file.
	 * @see java.io.FileInputStream
	 */
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	/**
	 * This implementation checks whether the underlying file is marked as writable
	 * (and corresponds to an actual file with content, not to a directory).
	 * @see java.io.File#canWrite()
	 * @see java.io.File#isDirectory()
	 */
	public boolean isWritable() {
		return (this.file.canWrite() && !this.file.isDirectory());
	}

	/**
	 * This implementation opens a FileOutputStream for the underlying file.
	 * @see java.io.FileOutputStream
	 */
	public OutputStream getOutputStream() throws IOException {
		return new FileOutputStream(this.file);
	}

	/**
	 * This implementation returns a URL for the underlying file.
	 * @see java.io.File#toURI()
	 */
	public URL getURL() throws IOException {
		return this.file.toURI().toURL();
	}

	/**
	 * This implementation returns a URI for the underlying file.
	 * @see java.io.File#toURI()
	 */
	public URI getURI() throws IOException {
		return this.file.toURI();
	}

	/**
	 * This implementation returns the underlying File reference.
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * This implementation returns the underlying File's length.
	 */
	public long contentLength() throws IOException {
		return this.file.length();
	}

	@Override
	public long lastModified() throws IOException {
		return 0;
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		return null;
	}

	@Override
	public String getFilename() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}


//	public Resource createRelative(String relativePath) {
//		String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
//		return new FileSystemResource(pathToUse);
//	}

//	/**
//	 * This implementation returns the name of the file.
//	 * @see java.io.File#getName()
//	 */
//	@Override
//	public String getFilename() {
//		return this.file.getName();
//	}
//
//	/**
//	 * This implementation returns a description that includes the absolute
//	 * path of the file.
//	 * @see java.io.File#getAbsolutePath()
//	 */
//	@Override
//	public String getDescription() {
//		return "file [" + this.file.getAbsolutePath() + "]";
//	}


	/**
	 * This implementation compares the underlying File references.
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj == this ||
			(obj instanceof FileSystemResource && this.path.equals(((FileSystemResource) obj).path)));
	}

	/**
	 * This implementation returns the hash code of the underlying File reference.
	 */
	@Override
	public int hashCode() {
		return this.path.hashCode();
	}

}
