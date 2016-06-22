/*
 * #%L
 * SCIFIO library for reading and converting scientific file formats.
 * %%
 * Copyright (C) 2011 - 2016 Board of Regents of the University of
 * Wisconsin-Madison
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package io.scif.qqq;

import io.scif.Metadata;
import io.scif.SCIFIOService;
import io.scif.config.SCIFIOConfig;

import java.io.IOException;

import org.scijava.io.Location;

/**
 * A service for opening and saving images using SCIFIO.
 *
 * @author Curtis Rueden
 */
public interface ImageIOService extends SCIFIOService {

	/**
	 * Determines whether the given source can be opened as a {@link RichImages}
	 * using the {@link #open(Location)} method.
	 */
	boolean canOpen(Location source);

	/**
	 * Determines whether the given destination can be used to save a
	 * {@link RichImages} using the {@link #save(RichImages, String)} method.
	 */
	boolean canSave(Location destination);

	/**
	 * Loads a dataset from a source (such as a file on disk).
	 */
	RichImages open(Location source) throws IOException;

	/**
	 * As {@link #open(String)}, with the given
	 * {@code io.scif.config.SCIFIOConfig}.
	 */
	RichImages open(Location source, SCIFIOConfig config) throws IOException;

	/**
	 * Reverts the given dataset to its original source.
	 */
	void revert(RichImages dataset) throws IOException;

	/**
	 * Saves a dataset to a destination (such as a file on disk).
	 *
	 * @param dataset The dataset to save.
	 * @param destination Where the dataset should be saved (e.g., a file path on
	 *          disk).
	 */
	Metadata save(RichImages dataset, String destination) throws IOException;

	/**
	 * Saves a dataset to a destination (such as a file on disk).
	 *
	 * @param dataset The dataset to save.
	 * @param destination Where the dataset should be saved (e.g., a file path on
	 *          disk).
	 * @param config The {@code io.scif.config.SCIFIOConfig} describing how the
	 *          data should be saved.
	 */
	Metadata save(RichImages dataset, String destination, SCIFIOConfig config)
		throws IOException;

}
