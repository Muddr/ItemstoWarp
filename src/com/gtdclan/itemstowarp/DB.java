package com.gtdclan.itemstowarp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "itw_data")
public class DB {
	
	@Id
	private int id;
	@NotNull
	private Boolean Isprivate;
	@NotNull
	private String Playername;
	@NotNull
	private String Warpname;
	@NotNull
	private String Warpworld;
	@NotNull
	private int Warpx;
	@NotNull
	private int Warpy;
	@NotNull
	private int Warpz;
	
	public int getId() {
		return id;
	}
	
	public Boolean getIsprivate() {
		return Isprivate;
	}
	
	public String getPlayername() {
		return Playername;
	}
	
	public String getWarpname() {
		return Warpname;
	}
	
	public String getWarpworld() {
		return Warpworld;
	}
	
	public int getWarpx() {
		return Warpx;
	}
	
	public int getWarpy() {
		return Warpy;
	}
	
	public int getWarpz() {
		return Warpz;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIsprivate(Boolean Isprivate) {
		this.Isprivate = Isprivate;
	}
	
	public void setPlayername(String Playername) {
		this.Playername = Playername;
	}
	
	public void setWarpname(String Warpname) {
		this.Warpname = Warpname;
	}
	
	public void setWarpworld(String Warpworld) {
		this.Warpworld = Warpworld;
	}
	
	public void setWarpx(int Warpx) {
		this.Warpx = Warpx;
	}
	
	public void setWarpy(int Warpy) {
		this.Warpy = Warpy;
	}
	
	public void setWarpz(int Warpz) {
		this.Warpz = Warpz;
	}
}
