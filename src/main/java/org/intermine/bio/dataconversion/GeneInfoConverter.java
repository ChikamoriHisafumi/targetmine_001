package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2019 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.io.Reader;
import java.util.Iterator;

import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.util.FormattedTextParser;
import org.intermine.xml.full.Item;

/**
 *
 * @author
 */

public class GeneInfoConverter extends BioFileConverter {
	//
	private static final String DATASET_TITLE = "Gene-Info";
	private static final String DATA_SOURCE_NAME = "TargetMine";

	/**
	 * Constructor
	 *
	 * @param writer the ItemWriter used to handle the resultant items
	 * @param model  the Model
	 */
	public GeneInfoConverter(ItemWriter writer, Model model) {
		super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
	}

	/**
	 *
	 *
	 * {@inheritDoc}
	 */
	public void process(Reader reader) throws Exception {
		Item organism = createItem("Organism");
		organism.setAttribute("name", "Human");
		organism.setAttribute("taxonId", "9606");
		store(organism);

		String[] columns = null;
		String id = "";
		String symbol = "";
		String name = "";

		Item item = null;

		Iterator<String[]> iterator = FormattedTextParser.parseTabDelimitedReader(reader);
		while (iterator.hasNext()) {

			columns = iterator.next();
			System.out.println(columns);
			
			id = columns[0];
			symbol = columns[1];
			name = columns[2];

			item = createItem("Gene");
			item.setAttribute("primaryIdentifier", id);
			item.setAttribute("symbol", symbol);
			item.setAttribute("name", name);
			item.setReference("organism", organism);
			store(item);

		}

	}
	
}
