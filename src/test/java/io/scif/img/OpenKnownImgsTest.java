
package io.scif.img;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import net.imglib2.exception.IncompatibleTypeException;

import org.junit.Test;
import org.scijava.Context;
import org.scijava.io.location.FileLocation;

/**
 * @author gabriel
 */
public class OpenKnownImgsTest {

	private final Context ctx = new Context();
	private final ImgOpener o = new ImgOpener(ctx);
	private final ImgSaver s = new ImgSaver(ctx);

	@Test
	public void testPNG() throws Exception {
		testImg("tiny-10x10x3.png", 10, 10, 3);
	}

	@Test
	public void testJPEG() throws Exception {
		testImg("tiny-10x10x3.jpg", 10, 10, 3);
	}

	public void testTinyTiff() throws Exception {
		testImg("tiny-10x10x3.tif", 10, 10, 3);
	}

	private void testImg(final String fileName, final int dim0, final int dim1,
		final int dim2) throws ImgIOException, IncompatibleTypeException,
		IOException
	{
		FileLocation sourceLoc = new FileLocation(this.getClass().getResource(
			fileName).getFile());
		final SCIFIOImgPlus<?> img = o.openImgs(sourceLoc).get(0);

		assertEquals(3, img.numDimensions());
		assertEquals(dim0, img.dimension(0));
		assertEquals(dim1, img.dimension(1));
		assertEquals(dim2, img.dimension(2));

		String[] split = fileName.split("\\.");
		FileLocation id = new FileLocation(File.createTempFile(split[0], "." + split[1]));
		s.saveImg(id, img);

		final SCIFIOImgPlus<?> iout = o.openImgs(id).get(0);

		assertEquals(3, iout.numDimensions());
		assertEquals(dim0, iout.dimension(0));
		assertEquals(dim1, iout.dimension(1));
		assertEquals(dim2, iout.dimension(2));
	}
}
