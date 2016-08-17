package com.ehi.msi.enterprisecarshare.util;

import java.util.Collection;

public final class CollectionUtils {

	private CollectionUtils() {
	}

	static public boolean containsCaseInsensitive(Collection<String> items,
			final String queryItem) throws IllegalArgumentException {

		if (items == null || queryItem == null) {
			throw new IllegalArgumentException();
		}

		for (String item : items) {
			if (queryItem.equalsIgnoreCase(item)) {
				return true;
			}
		}

		return false;
	}
}
