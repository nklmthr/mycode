package com.flipkart.logger.sink;

public enum GenericSinkEnum {

	FILE(new GenericTextFileSink()), DB(new GenericDatabaseSink()), CONSOLE(
			new GenericCosnoleSink());
	private Sink classObject;

	private GenericSinkEnum(Sink classObject) {
		this.classObject = classObject;
	}

	public Sink classObject() {
		return classObject;
	}
}
