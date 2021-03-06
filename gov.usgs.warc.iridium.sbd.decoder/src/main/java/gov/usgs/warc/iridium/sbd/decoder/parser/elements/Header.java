package gov.usgs.warc.iridium.sbd.decoder.parser.elements;

import gov.usgs.warc.iridium.sbd.decoder.parser.SessionStatus;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The directIP header information element
 *
 * @author darceyj
 * @since Jan 5, 2018
 *
 */
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Header
{
	/**
	 *
	 * @return a builder for the header
	 * @since Jan 5, 2018
	 */
	public static HeaderBuilder builder()
	{
		return new HeaderBuilder();
	}

	/**
	 * The auto id generated by the station
	 *
	 * @since Jan 26, 2018
	 */
	private final long	cdrId;
	/**
	 * The information element id.
	 *
	 * @since Jan 26, 2018
	 */
	private final char	id;
	/**
	 * The station's IMEI
	 *
	 * @since Jan 26, 2018
	 */
	private final long	imei;
	/**
	 * The length of the element
	 *
	 * @since Jan 26, 2018
	 */
	private final short	length;
	/**
	 * The mobile originated message sequence number
	 *
	 * @since Jan 26, 2018
	 */
	private final int	momsn;
	/**
	 * The mobile terminated message sequence number
	 *
	 * @since Jan 26, 2018
	 */
	private final int	mtmsn;

	/**
	 * Time of session
	 *
	 * @since Jan 26, 2018
	 */
	@Getter(AccessLevel.PRIVATE)
	private final long	sessionTime;

	/**
	 * The status of the transmission.
	 *
	 * @see SessionStatus#getStatus(int)
	 * @since Jan 26, 2018
	 */
	private final int	status;

	/**
	 * Get the {@link ZonedDateTime} from the epoch second from the header.
	 *
	 * @return the {@link ZonedDateTime} in UTC time from the epoch second from
	 *         the header.
	 * @since Jan 8, 2018
	 */
	public ZonedDateTime getZonedTimeFromSession()
	{
		final Instant ofEpochSecond = Instant.ofEpochSecond(getSessionTime());
		return ZonedDateTime.ofInstant(ofEpochSecond, ZoneId.of("UTC"));
	}

}
