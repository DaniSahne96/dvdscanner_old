package de.dani.dvdscanner.database;

public class DbRow {
	private long _id;
	private long _ean;
	private String _name;
	private int _fsk;
	
	public long get_id() {
		return _id;
	}
	public long get_ean() {
		return _ean;
	}
	public String get_name() {
		return _name;
	}
	public int get_fsk() {
		return _fsk;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public void set_ean(long _ean) {
		this._ean = _ean;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public void set_fsk(int _fsk) {
		this._fsk = _fsk;
	}
	
	@Override
	public String toString(){
		return _id +" " +_ean +" " +_name +" " +_fsk;
	}
}
