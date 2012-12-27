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
	@NotNull
	private float Warppitch;
	@NotNull
	private float Warpyaw;
	
	public int getId() {
		return this.id;
	}
	
	public Boolean getIsprivate() {
		return this.Isprivate;
	}
	
	public String getPlayername() {
		return this.Playername;
	}
	
	public String getWarpname() {
		return this.Warpname;
	}
	
	public float getWarppitch() {
		return this.Warppitch;
	}
	
	public String getWarpworld() {
		return this.Warpworld;
	}
	
	public int getWarpx() {
		return this.Warpx;
	}
	
	public int getWarpy() {
		return this.Warpy;
	}
	
	public float getWarpyaw() {
		return this.Warpyaw;
	}
	
	public int getWarpz() {
		return this.Warpz;
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
	
	public void setWarppitch(float warppitch) {
		this.Warppitch = warppitch;
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
	
	public void setWarpyaw(float warpyaw) {
		this.Warpyaw = warpyaw;
	}
	
	public void setWarpz(int Warpz) {
		this.Warpz = Warpz;
	}
}
