package com.cg.ngoportal.services.test;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.NeedyPeopleServiceImpl;

@SpringBootTest
class NeedyPeopleServiceImplTest {

	@Mock
	NeedyPeopleDao needyPeopleRepoMock;

	@Mock
	UserDao userRepoMock;

	@Mock
	RequestDao requestRepoMock;

	@InjectMocks
	NeedyPeopleServiceImpl needyPeopleService;

	List<NeedyPeople> needyPeopleList;
	NeedyPeople needyPerson1, needyPerson2, needyPerson3;

	List<User> userList;
	User user1, user2;

	List<Request> requestList;
	Request request1, request2;

	@SuppressWarnings("deprecation")
	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		needyPeopleList = new ArrayList<>();
		userList = new ArrayList<>();

		user1 = new User("pawan", "pk", UserType.NEEDYPERSON);
		user2 = new User("nikita", "ns", UserType.NEEDYPERSON);

		needyPerson1 = new NeedyPeople("Pawan", "7777788888", 35000, user1,
				new Address("Mumbai", "Maharashtra", "412123", "Marine Drive"));

		needyPerson2 = new NeedyPeople("Nikita", "8888899999", 52000, user2,
				new Address("Pune", "Maharashtra", "410400", "Tilak Road"));

		needyPerson3 = new NeedyPeople("Pawan Khedkar", "7777788888", 150000, user1,
				new Address("Mumbai", "Maharashtra", "412123", "Marine Drive"));

		needyPeopleList.add(needyPerson1);
		needyPeopleList.add(needyPerson2);
		needyPeopleList.add(needyPerson3);

		requestList = new ArrayList<>();

		request1 = new Request(needyPerson1.getId(), DonationType.BOOKS, 5, "Library");
		request2 = new Request(needyPerson2.getId(), DonationType.MONEY, 15000, "Medical");

		requestList.add(request1);
		requestList.add(request2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Passed.
	@Test
	void testRegister() throws DuplicateNeedyPersonException {
		when(needyPeopleRepoMock.save(needyPerson1)).thenReturn(needyPerson1);
		Assertions.assertEquals(needyPerson1, needyPeopleService.register(needyPerson1));

	}

	// Passed.
	@Test
	void testLoginNeedyPerson() throws NoSuchNeedyPersonException {
		when(userRepoMock.findByUsernameAndPassword("pawan", "pk"))
				.thenReturn(Optional.of(needyPerson1.getUserLoginDetails()));
		when(needyPeopleRepoMock.findByUserLoginDetails(needyPerson1.getUserLoginDetails()))
				.thenReturn(Optional.of(needyPerson1));
		Assertions.assertEquals("Logged In as "+user1.getUsername(), needyPeopleService.loginNeedyPerson("pawan", "pk"));
	}

	// Passed.
	@Test
	void testRequestForHelp() throws UserNotLoggedInException {
		when(requestRepoMock.save(request1)).thenReturn(request1);
		Assertions.assertEquals(request1, needyPeopleService.requestForHelp(request1));

	}

	// Passed.
	@Test
	void testRequestStatusCheck() throws UserNotLoggedInException {
		when(requestRepoMock.findByNeedyPersonId(-1)).thenReturn(requestList);
		Assertions.assertEquals(requestList, needyPeopleService.requestStatusCheck());
	}

	// Passed.
	@Test
	void testModifyNeedyPerson() throws NoSuchNeedyPersonException, UserNotLoggedInException {
		when(needyPeopleRepoMock.findById(-1)).thenReturn(Optional.of(needyPerson1));
		when(needyPeopleRepoMock.save(needyPerson1)).thenReturn(needyPerson3);
		Assertions.assertEquals(needyPerson3, needyPeopleService.modifyNeedyPerson(needyPerson3));
	}

	// Passed.
	@Test
	void testLogOut() {
		Assertions.assertEquals("Logged Out!", needyPeopleService.logOut());
	}

}