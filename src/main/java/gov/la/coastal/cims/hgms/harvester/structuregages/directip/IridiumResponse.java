package gov.la.coastal.cims.hgms.harvester.structuregages.directip;

import com.google.common.collect.Table;

import java.util.Collection;

import gov.la.coastal.cims.hgms.harvester.structuregages.parser.Message;
import gov.usgs.warc.iridium.sbd.decoder.db.entity.IridiumDataType;
import gov.usgs.warc.iridium.sbd.decoder.db.entity.IridiumStationId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A response from the iridium that will hold the {@link Message} and its
 * corresponding map of values
 *
 * @author darceyj
 * @since Feb 12, 2018
 *
 */
@Data
@EqualsAndHashCode
@ToString
@Builder
public class IridiumResponse
{
	/**
	 * The directip {@link Message}
	 *
	 * @since Feb 12, 2018
	 */
	private Message												message;

	/**
	 * The stations related to this response
	 *
	 * @since Feb 12, 2018
	 */
	private Collection<? extends IridiumStationId>				stations;

	/**
	 * The map of datatypes to their corresponding values
	 *
	 * @since Feb 12, 2018
	 */
	private Table<IridiumStationId, IridiumDataType, Double>	values;

}
