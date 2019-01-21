package com.nklmthr.an.utils.domain;

import com.nklmthr.an.utils.dto.Address;

public abstract class ProfileManager {

	public static void clearAllAdditionalAddress() {
		// TODO Auto-generated method stub

	}

	public Address createCountryAddress(String country) {
		return createCountrySpecific(country);
	}

	public abstract Address createCountrySpecific(String country);

}
