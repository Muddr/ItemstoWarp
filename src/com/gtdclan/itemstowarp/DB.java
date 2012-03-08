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
	@NotNull
	private Boolean Isprivate;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPlayername() {
		return Playername;
	}
	
	public void setPlayername(String Playername) {
		this.Playername = Playername;
	}
	
	public String getWarpname() {
		return Warpname;
	}
	
	public void setWarpname(String Warpname) {
		this.Warpname = Warpname;
	}
	
	public String getWarpworld() {
		return Warpworld;
	}
	
	public void setWarpworld(String Warpworld) {
		this.Warpworld = Warpworld;
	}
	
	public int getWarpx() {
		return Warpx;
	}
	
	public void setWarpx(int Warpx) {
		this.Warpx = Warpx;
	}
	
	public int getWarpy() {
		return Warpy;
	}
	
	public void setWarpy(int Warpy) {
		this.Warpy = Warpy;
	}
	
	public int getWarpz() {
		return Warpz;
	}
	
	public void setWarpz(int Warpz) {
		this.Warpz = Warpz;
	}
	
	public Boolean getIsprivate() {
		return Isprivate;
	}
	
	public void setIsprivate(Boolean Isprivate) {
		this.Isprivate = Isprivate;
	}
}
