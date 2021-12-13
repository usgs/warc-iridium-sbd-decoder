package gov.usgs.warc.iridium.sbd.decoder.sixbitbinary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Maps;
import gov.usgs.warc.iridium.sbd.decoder.Tests;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link BitString}
 *
 * @author mckelvym
 * @since Jan 5, 2018
 *
 */
public class BitStringTest
{

	/**
	 * @throws java.lang.Exception
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	@BeforeAll
	public static void setUpBeforeAll() throws Exception
	{
		final Class<?> classToTest = BitString.class;
		final Class<?> testingClass = BitStringTest.class;
		Tests.assertHasRequiredMethods(classToTest, testingClass);

	}

	/**
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	private Map<String, Integer> m_TestValues;

	/**
	 *
	 * @throws java.lang.Exception
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		m_TestValues = Maps.newHashMap();
		m_TestValues.put("11000000111001", 12345);
		m_TestValues.put("111100111111000111", -12345);
		m_TestValues.put("000000010011010111", 1239);
	}

	/**
	 * Test method for
	 * {@link gov.usgs.warc.iridium.sbd.decoder.sixbitbinary.BitString#parseTwosComplement(java.lang.String)}.
	 */
	@Test
	public void testParseTwosComplement()
	{
		for (final Entry<String, Integer> entry : m_TestValues.entrySet())
		{
			assertEquals(entry.getValue().intValue(),
					BitString.parseTwosComplement(entry.getKey()).intValue(),
					entry.getKey());
		}
	}

}
