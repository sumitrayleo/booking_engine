package com.ehi.msi.enterprisecarshare.serializer;

/**
 * Interface for Serializer. Contains a method which needs to be implemented by
 * every kind of serializer.
 * 
 */
public interface ISerializer {

	/**
	 * Method to convert the source object into string
	 * 
	 * @param object
	 *            to be serialized to String
	 * @return serialized String result
	 */
	String convert(final Object object);
}