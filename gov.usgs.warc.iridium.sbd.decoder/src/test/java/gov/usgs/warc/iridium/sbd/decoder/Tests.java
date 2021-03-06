package gov.usgs.warc.iridium.sbd.decoder;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import org.springframework.context.annotation.Bean;

/**
 * Test utilities
 *
 * @author mckelvym
 * @since Jul 28, 2015
 *
 */
public final class Tests
{
	/**
	 * @author mckelvym
	 * @since Nov 27, 2017
	 *
	 */
	public static enum IncludeField
	{
		/**
		 * Include _links documentation
		 *
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		LINKS,
		/**
		 * Include _links.templated documentation
		 *
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		LINKS_TEMPLATED,
		/**
		 * Include page documentation
		 *
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		PAGINATION,
		/**
		 * Include profile documentation
		 *
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		PROFILE,;

		/**
		 * @return set with all {@link #values()}
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		public static Set<IncludeField> all()
		{
			return Sets.newHashSet(IncludeField.values());
		}

		/**
		 * @return set with only {@link IncludeField#LINKS}
		 * @author mckelvym
		 * @since Nov 27, 2017
		 */
		public static Set<IncludeField> linksOnly()
		{
			return Sets.newHashSet(IncludeField.LINKS);
		}
	}

	/**
	 * Methods to skip for requiring tests.
	 *
	 * @author mckelvym
	 * @since Oct 4, 2017
	 *
	 */
	public enum SkipMethod
	{
		/**
		 * Skip "builder" method if generated by Lombok.
		 *
		 * @author mckelvym
		 * @since Oct 3, 2017
		 */
		BUILDER,
		/**
		 * Skip "canEqual" method, which is often generated by Lombok
		 *
		 * @author mckelvym
		 * @since Oct 3, 2017
		 */
		CAN_EQUAL,
		/**
		 * Skip "equals" and "hashCode" method if generated by AutoValue or
		 * Lombok.
		 *
		 * @author mckelvym
		 * @since Oct 3, 2017
		 */
		EQUALS_AND_HASHCODE,
		/**
		 * Skip "toString" method if not relevant
		 *
		 * @author mckelvym
		 * @since Oct 3, 2017
		 */
		TO_STRING,
	}

	/**
	 * Test name prefix.
	 *
	 * @since Jul 28, 2015
	 */
	private static final String PREFIX = "test";

	/**
	 * Ensure that a testing class has defined all required testing methods.
	 *
	 * @param p_ClassToTest
	 *            the class that is being tested
	 * @param p_TestingClass
	 *            the class that is doing the testing
	 * @param p_Methods
	 *            methods to skip, as defined in {@link SkipMethod}
	 * @throws NoSuchMethodException
	 *             if a method is missing.
	 * @since Jul 28, 2015
	 */
	public static void assertHasRequiredMethods(final Class<?> p_ClassToTest,
			final Class<?> p_TestingClass, final SkipMethod... p_Methods)
			throws NoSuchMethodException
	{
		final Set<SkipMethod> skips = Sets.newHashSet(p_Methods);
		final Collection<String> missingRequiredTestMethods = Tests
				.getMissingRequiredTestMethods(p_ClassToTest, p_TestingClass);
		for (final SkipMethod skip : skips)
		{
			switch (skip)
			{
				case CAN_EQUAL:
					final String canEqual = PREFIX + "CanEqual";
					if (missingRequiredTestMethods.contains(canEqual))
					{
						missingRequiredTestMethods.remove(canEqual);
					}
					break;
				case EQUALS_AND_HASHCODE:
					final String equals = PREFIX + "Equals";
					if (missingRequiredTestMethods.contains(equals))
					{
						missingRequiredTestMethods.remove(equals);
					}

					final String hashCode = PREFIX + "HashCode";
					if (missingRequiredTestMethods.contains(hashCode))
					{
						missingRequiredTestMethods.remove(hashCode);
					}
					break;
				case TO_STRING:
					final String toString = PREFIX + "ToString";
					if (missingRequiredTestMethods.contains(toString))
					{
						missingRequiredTestMethods.remove(toString);
					}
					break;
				case BUILDER:
					final String builder = PREFIX + "Builder";
					if (missingRequiredTestMethods.contains(builder))
					{
						missingRequiredTestMethods.remove(builder);
					}
					break;
				default:
					throw new UnsupportedOperationException(
							"Not implemented: " + skip);
			}
		}

		if (!missingRequiredTestMethods.isEmpty())
		{
			throw new NoSuchMethodException(
					String.format("Methods %s missing in %s.",
							Joiner.on(", ").join(missingRequiredTestMethods),
							p_TestingClass.getSimpleName()));
		}
	}

	/**
	 * Gets the publicly-visible methods defined by the provided testing class
	 * that start with the "test" prefix.
	 *
	 * @param p_TestingClass
	 *            the test doing the testing
	 * @return an Collection of public method names starting with the prefix
	 * @since Jul 28, 2015
	 */
	private static Collection<String> getDefinedTestMethods(
			final Class<?> p_TestingClass)
	{
		final SortedSet<String> methods = Sets.newTreeSet();
		for (final Method method : p_TestingClass.getMethods())
		{
			if (!method.getName().startsWith(PREFIX))
			{
				continue;
			}

			final StringBuilder builder = new StringBuilder(method.getName());
			methods.add(builder.toString());
		}
		return methods;
	}

	/**
	 * Get the methods that are listed as being required but are not defined.
	 *
	 * @param p_ClassToTest
	 *            the class that is being tested
	 * @param p_TestingClass
	 *            the class that is doing the testing
	 * @return the required method names not present in the defined method names
	 * @since Jul 28, 2015
	 */
	private static Collection<String> getMissingRequiredTestMethods(
			final Class<?> p_ClassToTest, final Class<?> p_TestingClass)
	{
		final Collection<String> requiredTestMethods = getRequiredTestMethods(
				p_ClassToTest);
		final Collection<String> definedTestMethods = getDefinedTestMethods(
				p_TestingClass);
		return getMissingRequiredTestMethods(requiredTestMethods,
				definedTestMethods);
	}

	/**
	 * Get the methods that are listed as being required but are not defined.
	 *
	 * @param p_RequiredMethodNames
	 *            the required method names
	 * @param p_DefinedMethodNames
	 *            all defined method names
	 * @return the required method names not present in the defined method names
	 * @since Jul 28, 2015
	 */
	private static Collection<String> getMissingRequiredTestMethods(
			final Collection<String> p_RequiredMethodNames,
			final Collection<String> p_DefinedMethodNames)
	{
		final SortedSet<String> methods = Sets
				.newTreeSet(p_RequiredMethodNames);
		methods.removeAll(Sets.newHashSet(p_DefinedMethodNames));
		return methods;
	}

	/**
	 * Get the non-private method names that are overloaded (having the same
	 * name
	 *
	 * @param p_Class
	 *            the class to check for declared methods
	 * @return the non-private method names that are duplicated
	 * @since Jul 28, 2015
	 */
	private static Collection<String> getOverloadedMethods(
			final Class<?> p_Class)
	{
		final SortedSet<String> methods = Sets.newTreeSet();
		final SortedSet<String> overloadedMethods = Sets.newTreeSet();
		for (final Method method : p_Class.getDeclaredMethods())
		{
			if (Modifier.isPrivate(method.getModifiers()))
			{
				continue;
			}
			final String elementName = method.getName();
			if (methods.contains(elementName))
			{
				overloadedMethods.add(elementName);
			}
			else
			{
				methods.add(elementName);
			}
		}
		return overloadedMethods;
	}

	/**
	 * Get the names of methods that should be defined in the testing class
	 *
	 * @param p_ClassToTest
	 *            the class to be tested
	 * @return the names of testing methods that should be defined by a testing
	 *         class
	 * @since Jul 28, 2015
	 */
	@SuppressWarnings("null")
	private static Collection<String> getRequiredTestMethods(
			final Class<?> p_ClassToTest)
	{
		final SortedSet<String> methods = Sets.newTreeSet();
		final SortedSet<String> overloadedMethods = Sets
				.newTreeSet(getOverloadedMethods(p_ClassToTest));
		for (final Method method : p_ClassToTest.getDeclaredMethods())
		{
			/**
			 * Skip @Bean methods.
			 */
			if (method.getAnnotation(Bean.class) != null)
			{
				continue;
			}

			@SuppressWarnings("unused")
			final int modifiers = method.getModifiers();
			if (Modifier.isPrivate(modifiers) || Modifier.isAbstract(modifiers))
			{
				continue;
			}

			final String elementName = method.getName();
			if (elementName.contains("$"))
			{
				continue;
			}
			if (p_ClassToTest.isEnum())
			{
				/**
				 * Skip valueOf(class, String)
				 */
				final Class<?>[] parameterTypes = method.getParameterTypes();
				if (elementName.equals("valueOf") && parameterTypes.length == 1
						&& parameterTypes[0].getSimpleName().equals("String"))
				{
					continue;
				}
				/**
				 * Skip values() which returns an array of this same type
				 */
				if (elementName.equals("values")
						&& method.getReturnType().isArray()
						&& method.getReturnType().getComponentType()
								.equals(p_ClassToTest))
				{
					continue;
				}
			}

			final StringBuilder builder = new StringBuilder(PREFIX);
			builder.append(Character.toUpperCase(elementName.charAt(0)))
					.append(elementName.substring(1));

			if (overloadedMethods.contains(elementName))
			{
				final Class<?>[] parameterTypes = method.getParameterTypes();
				for (final Class<?> parameter : parameterTypes)
				{
					/**
					 * Do not require compareToObject and compareTo{ThisType}.
					 * Only require only compareTo test.
					 */
					if (elementName.equalsIgnoreCase("compareTo")
							&& parameterTypes.length == 1)
					{
						break;
					}

					String psimpleName = parameter.getSimpleName();
					psimpleName = psimpleName.replace("[]", "Array");
					final StringBuilder psimpleNameBuilder = new StringBuilder(
							psimpleName);
					psimpleNameBuilder.setCharAt(0, Character
							.toUpperCase(psimpleNameBuilder.charAt(0)));
					builder.append(psimpleNameBuilder);
				}

			}
			replaceIllegalCharacters(builder);
			methods.add(builder.toString());
		}
		return methods;
	}

	/**
	 * From jdt.junit.wizards.NewTestCaseWizardPageOne.java
	 *
	 * @param p_Buffer
	 *            the string buffer
	 * @since Jul 28, 2015
	 */
	private static void replaceIllegalCharacters(final StringBuilder p_Buffer)
	{
		char character = 0;
		for (int index = p_Buffer.length() - 1; index >= 0; index--)
		{
			character = p_Buffer.charAt(index);
			if (Character.isWhitespace(character))
			{
				p_Buffer.deleteCharAt(index);
			}
			else if (character == '<')
			{
				p_Buffer.replace(index, index + 1, "Of");
			}
			else if (character == '?')
			{
				p_Buffer.replace(index, index + 1, "Q");
			}
		}
	}

	/**
	 * @since Jul 28, 2015
	 */
	private Tests()
	{
	}

}
