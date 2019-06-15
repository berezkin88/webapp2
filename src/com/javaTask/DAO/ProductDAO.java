package com.javaTask.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.javaTask.DAO.connection.ConnectionAndStatementFactory;
import com.javaTask.model.Product;

public class ProductDAO {

	private final static Logger LOG = Logger.getLogger(ProductDAO.class.getName());

	public static void insert(Product product) throws SQLException {
		Statement statement = null;
		Connection connection = null;
		
		try {
			connection = ConnectionAndStatementFactory.connecting();

			LOG.info("inserting into PRODUCT table...");
			statement = ConnectionAndStatementFactory.createStatement(connection);

			String sql = "INSERT INTO PRODUCT (title, price, description) VALUES ( '" + product.getTitle() + "', "
					+ product.getPrice() + ", '" + product.getDescription() + "')";

			statement.execute(sql);
			LOG.info("insertion complete");
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static List<Product> getAll() throws SQLException {
		Statement statement = null;
		Connection connection = null;
		List<Product> result = new ArrayList<>();

		try {
			connection = ConnectionAndStatementFactory.connecting();

			LOG.info("reading from PRODUCT table...");
			statement = ConnectionAndStatementFactory.createStatement(connection);

			String SQL = "SELECT * FROM PRODUCT";

			ResultSet resultSet = statement.executeQuery(SQL);
			LOG.info("reading completed");

			while (resultSet.next()) {
				try {
					result.add(createProduct(resultSet));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return result;
	}

	public static void updateById(Product product) throws SQLException {
		Statement statement = null;
		Connection connection = null;
		
		try {
			connection = ConnectionAndStatementFactory.connecting();

			LOG.info("updating PRODUCT table...");
			statement = ConnectionAndStatementFactory.createStatement(connection);

			String SQL = "UPDATE PRODUCT SET title = '" + product.getTitle() + "', price = " + product.getPrice()
					+ ", description = '" + product.getDescription() + "' WHERE id = " + product.getId();

			statement.execute(SQL);
			LOG.info("update complete");
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void delete(Product product) throws SQLException {
		Statement statement = null;
		Connection connection = null;
		
		try {
			connection = ConnectionAndStatementFactory.connecting();

			LOG.info("deleting from PRODUCT table...");
			statement = ConnectionAndStatementFactory.createStatement(connection);

			String SQL = "DELETE FROM PRODUCT WHERE id = " + product.getId();

			statement.execute(SQL);
			LOG.info("deleting complete");
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static Product getOneById(int id) throws SQLException {
		Statement statement = null;
		Connection connection = null;
		Product product = new Product();

		try {
			connection = ConnectionAndStatementFactory.connecting();

			LOG.info("seaching by id through PRODUCT table...");
			statement = ConnectionAndStatementFactory.createStatement(connection);

			String SQL = "SELECT * FROM PRODUCT WHERE id = " + id + "LIMIT 1";

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next())
				product = createProduct(resultSet);
			LOG.info("seaching complete");
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return product;
	}

	private static Product createProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();

		product.setId(resultSet.getInt("id"));
		product.setTitle(resultSet.getString("title"));
		product.setPrice(resultSet.getDouble("price"));
		product.setDescription(resultSet.getString("description"));

		return product;
	}
}
