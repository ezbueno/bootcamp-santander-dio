package com.ezandro.exceptions;

import com.ezandro.util.MessageUtils;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(MessageUtils.NO_RECORDS_FOUND);
	}

}