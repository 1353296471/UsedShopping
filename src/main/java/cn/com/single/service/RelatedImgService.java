package cn.com.single.service;

import java.util.List;

import cn.com.single.entity.Imags;

public interface RelatedImgService {
	public List<Imags> findImgUrls(int proId);

	public String findProImag(int proId);
}
