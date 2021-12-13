package gov.usgs.warc.iridium.sbd.decoder.sixbitbinary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Maps;
import gov.usgs.warc.iridium.sbd.decoder.Tests;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link Decode}
 *
 * @author mckelvym
 * @since Jan 5, 2018
 *
 */
public class DecodeTest
{
	/**
	 * @throws java.lang.Exception
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	@BeforeAll
	public static void setUpBeforeAll() throws Exception
	{
		final Class<?> classToTest = Decode.class;
		final Class<?> testingClass = DecodeTest.class;
		Tests.assertHasRequiredMethods(classToTest, testingClass);

	}

	/**
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	private Map<List<Byte>, Integer> m_TestValuesFromBytes;

	/**
	 * @throws java.lang.Exception
	 * @author mckelvym
	 * @since Jan 5, 2018
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		m_TestValuesFromBytes = Maps.newHashMap();

		m_TestValuesFromBytes.put(Arrays.asList((byte) 78), 14);
		m_TestValuesFromBytes
				.put(Arrays.asList((byte) 64, (byte) 64, (byte) 92), 28);
		m_TestValuesFromBytes
				.put(Arrays.asList((byte) 66, (byte) 96, (byte) 101), 10277);
		m_TestValuesFromBytes
				.put(Arrays.asList((byte) 64, (byte) 64, (byte) 64), 0);
	}

	/**
	 * Test method for
	 * {@link gov.usgs.warc.iridium.sbd.decoder.sixbitbinary.Decode#valueAtIndex(List, int, int, float)}.
	 */
	@Test
	public void testValueAtIndex()
	{
		final List<Byte> bytes = Arrays.asList((byte) 64, (byte) 66, (byte) 96,
				(byte) 101, (byte) 69);
		assertEquals(1027.7f, Decode.valueAtIndex(bytes, 1, 3, 10).floatValue(),
				Float.MIN_NORMAL);
	}

	/**
	 * Test method for
	 * {@link gov.usgs.warc.iridium.sbd.decoder.sixbitbinary.Decode#valueFromBytes(java.util.List)}.
	 */
	@Test
	public void testValueFromBytes()
	{
		for (final Entry<List<Byte>, Integer> entry : m_TestValuesFromBytes
				.entrySet())
		{
			assertEquals(entry.getValue().intValue(),
					Decode.valueFromBytes(entry.getKey()).intValue(),
					Arrays.toString(entry.getKey().toArray()));
		}
	}

}
