package io.trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.trade.model.UsersDetails;
import io.trade.model.UserRoles;
import io.trade.model.Users;
import io.trade.repository.UserDetailsRepository;
import io.trade.repository.UserRoleRepository;
import io.trade.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UsersRepository users;
	private final UserRoleRepository roles;
	private final UserDetailsRepository details;
	private final DatabaseService database;

	@Autowired
	public UserServiceImpl(UsersRepository users, UserRoleRepository roles, UserDetailsRepository details,
			DatabaseService db) {
		this.users = users;
		this.roles = roles;
		this.details = details;
		this.database = db;
		this.database.setUsers(this);
	}

	@Override
	public List<UsersDetails> findAllDetails() {
		return details.findAll();
	}

	@Override
	public UsersDetails findDetailsByUser(Users user) {
		List<UsersDetails> d = details.findByUsers(user);
		if (d.isEmpty())
			return null;
		return d.get(0);
	}

	@Override
	public void addDetails(UsersDetails usersDetails) {
		try {
			details.save(usersDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserRoles> findAllRoles() {
		return roles.findAll();
	}

	@Override
	public UserRoles findRolesByUser(Users user) {
		List<UserRoles> r = roles.findByUsers(user);
		if (r.isEmpty())
			return null;
		return r.get(0);
	}

	@Override
	public void addRoles(UserRoles userRoles) {
		try {
			roles.save(userRoles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Users> findAll() {
		return users.findAll();
	}

	@Override
	public Users findByUserName(String userName) {
		List<Users> r = users.findByUserName(userName);
		if (r.isEmpty())
			return null;
		return r.get(0);
	}

	@Override
	public void add(Users user) {
		try {
			users.save(user);
			if (roles.findByUsers(user) == null)
				roles.save(new UserRoles(user, "ROLE_USER"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
