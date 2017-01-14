package org.glycoinfo.batch.wurcs.subsumption.composition.app;

import org.glycoinfo.batch.glyconvert.ConvertSelectSparql;
import org.glycoinfo.batch.glyconvert.GlyConvertSparqlItemConfig;
import org.glycoinfo.convert.GlyConvert;
import org.glycoinfo.convert.GlyConvertConfig;
import org.glycoinfo.convert.wurcs.WurcsToWurcsCompositionConverter;
import org.glycoinfo.rdf.InsertSparql;
import org.glycoinfo.rdf.SelectSparql;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.glycoinfo.rdf.service.impl.GlycanProcedureConfig;
import org.glycoinfo.rdf.wurcs.CompositionConvertSelectSparql;
import org.glycoinfo.rdf.wurcs.MonosaccharideCompositionInsertSparql;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import({VirtSesameTransactionConfig.class, GlycanProcedureConfig.class, GlyConvertConfig.class, ProcessorConfig.class})
public class Config {

	// to set the graph to insert into.
	public static String graph = "http://rdf.glytoucan.org/composition";

	@Primary
	@Bean(name="org.glycoinfo.batch.glyconvert")
	GlyConvert getGlyConvert() {
		return new WurcsToWurcsCompositionConverter();
	}
	
	@Bean(name = "itemReaderSelectSparql")
	SelectSparql itemReaderSelectSparql() {
		SelectSparql select = new CompositionConvertSelectSparql();
		select.setFrom("FROM <http://rdf.glytoucan.org/core> FROM <http://rdf.glytoucan.org/sequence/wurcs> FROM <http://rdf.glytoucan.org/topology> FROM <" + graph + ">");
		select.setOrderBy(ConvertSelectSparql.AccessionNumber);
		return select;
	}

	@Bean(name = "itemWriterInsertSparql")
	InsertSparql getInsertSparql() {
	  MonosaccharideCompositionInsertSparql convert = new MonosaccharideCompositionInsertSparql();
		convert.setGraph(graph);
		return convert;
	}
}
