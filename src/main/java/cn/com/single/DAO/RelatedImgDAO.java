package cn.com.single.DAO;

import java.util.List;

import cn.com.single.entity.Imags;

public interface RelatedImgDAO {
	public List<Imags> findImgUrls(int proId);

	public String findProImag(int proId);

}
