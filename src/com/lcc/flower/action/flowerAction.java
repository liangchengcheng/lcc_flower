package com.lcc.flower.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.lcc.flower.model.Flower;
import com.lcc.flower.service.ICatalogService;
import com.lcc.flower.service.IFlowerService;
import com.lcc.flower.util.Pager;
import com.lcc.flower.util.uploadzp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class flowerAction extends ActionSupport {

	private ICatalogService catalogService;
	private IFlowerService flowerService;
	private int catalogid;
	private int currentPage = 1;
	private Flower flower;
	private String picture;
	private File upload;
	private String uploadFileName;
	private int flowerid;

	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public IFlowerService getFlowerService() {
		return flowerService;
	}

	public void setFlowerService(IFlowerService flowerService) {
		this.flowerService = flowerService;
	}

	public int getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public int getFlowerid() {
		return flowerid;
	}

	public void setFlowerid(int flowerid) {
		this.flowerid = flowerid;
	}

	// ========
	public String browseCatalog() throws Exception {
		List catalogs = catalogService.getAllCatalogs();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("catalogs", catalogs);
		return SUCCESS;
	}

	public String browseNewFlower() throws Exception {
		List newflowers = flowerService.getNewFlower();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("newflowers", newflowers);
		return SUCCESS;
	}

	public String browseFlowerPaging() throws Exception {
		int totalSize = flowerService.getTotalByCatalog(catalogid);
		Pager pager = new Pager(currentPage, totalSize);
		List flowers = flowerService.getFlowerByCatalogidPaging(catalogid, currentPage, pager.getPageSize());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("flowers", flowers);
		request.put("page", pager);
		return SUCCESS;
	}

	public String addOrUpdateFlower() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath("/pic");

		List list = flowerService.getNewFlower();
		Flower flower1 = (Flower) list.get(0);
		int currentFlowerID = flower1.getFlowerid() + 1;

		path = path + "\\" + currentFlowerID + this.getUploadFileName();
		new uploadzp().upload(this.getUpload(), path);
		flower.setPicture(currentFlowerID + this.getUploadFileName());

		Flower flower2 = new Flower();
		flower2.setCatalog(flower.getCatalog());
		flower2.setFlowername(flower.getFlowername());
		if (flower.getPicture() == null) {
			flower2.setPicture(picture);
		} else {
			flower2.setPicture(flower.getPicture());
			flower2.setPrice(flower.getPrice());
			flower2.setFlowerid(flower.getFlowerid());
		}
		if (flowerService.addOrUpdateFlower(flower2)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String browseAllFlowerPaging() throws Exception {
		List flowers = flowerService.getAllFlower();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("flowers", flowers);
		return SUCCESS;
	}
	
	public String displayOneFlower()throws Exception{
		Flower modifyFlower = flowerService.getFlowerById(flowerid);
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("flower", modifyFlower);
		return SUCCESS;
	}
	
	public String deleteOneFlower()throws Exception{
		flowerService.deleteFlowerById(flowerid);
		return SUCCESS;
	}
}
