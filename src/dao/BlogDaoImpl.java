package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{

	@Override
	public void insertBlog(Blog blog) throws SQLException, ClassNotFoundException  {
		// TODO Auto-generated method stub
		String sql="INSERT INTO BLOG VALUES(?,?,?)";
		PreparedStatement st=ConnectionManager.getConnection().prepareStatement(sql);
		st.setInt(1, blog.getBlogId());
		st.setString(2, blog.getBlogTitle());
		st.setString(3, blog.getBlogDescription());
		
		st.executeUpdate();

	}

	@Override
	public List selectAllBlogs() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM BLOG";	
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		ResultSet result = st.executeQuery();
		List<Blog> list = new ArrayList<Blog>();	
		while(result.next()) {
			int id = result.getInt("ID");
			String title = result.getString("TITLE");
			String desc = result.getString("DESCRIPTION");
			
			Blog blog = new Blog();
			blog.setBlogId(id);
			blog.setBlogTitle(title);
			blog.setBlogDescription(desc);
			list.add(blog);
			ConnectionManager.getConnection().close();
		}
		return list;
	}
}