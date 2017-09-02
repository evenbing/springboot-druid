package com.easybcp.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybcp.sys.model.SysUsers;
import com.easybcp.sys.service.SysUsersService;
import com.easybcp.utils.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "sys用户管理-tags", value = "用户管理-value")
@RestController
@RequestMapping("/sysusers")
public class SysUsersController {

	@Autowired
	private SysUsersService usersBeanService;

	@ApiOperation("查询用户列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<SysUsers>> getUsers() {
		List<SysUsers> users = usersBeanService.selectAll();

		return new BaseResponse<List<SysUsers>>(users);

	}

	@ApiOperation("查询用户明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<SysUsers> getUser(@ApiParam("用户id") @PathVariable Long id) {
		SysUsers user = usersBeanService.selectOne(id);
		return new BaseResponse<SysUsers>(user);
	}

	@ApiOperation("创建用户")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	// @Transactional
	public BaseResponse<Integer> save(@ApiParam("用户实体") @RequestBody SysUsers user) {

		int result = usersBeanService.insert(user);

		return new BaseResponse<Integer>(result);
	}

	@ApiOperation("修改用户")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse<Integer> update(@ApiParam("用户实体") @RequestBody SysUsers user) {
		int result = usersBeanService.update(user);
		return new BaseResponse<Integer>(result);
	}

	@ApiOperation("删除用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse<Integer> delete(@ApiParam("用户id") @PathVariable("id") Long id) {
		Integer result = usersBeanService.delete(id);
		return new BaseResponse<Integer>(result);
	}

	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Integer> test2(@RequestBody SysUsers user) {
		Integer tt = usersBeanService.test(user);

		return new BaseResponse<Integer>(tt);

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test() {
		SysUsers user = new SysUsers();
		user.setId(50L);
		user.setUsername("test");
		for (int i = 0; i < 2; i++) {
			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {
					usersBeanService.save(user);
					System.out.println("run");
				}

			});
			th.start();

		}

	}

}