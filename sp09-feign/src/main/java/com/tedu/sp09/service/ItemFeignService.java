package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;

//通过指定服务id,可以知道向哪一台主机来转发调用
@FeignClient(name="item-service", fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/*
	 * 目前地类并非控制器Controller
	 * 但是有SpringMVC注解
	 * 	feign利用了SpringMVC注解来反向生成访问路径
	 * 		根据Mapping中指定地路径,在主机地址后面拼接路径
	 * 		http://localhost:8001/{orderId}
	 * 			根据@PathVariable配置,把参数数据,拼接到路径当中
	 * 			假设参数是"123",路径:http://localhost:8001/123
	 * 			然后向拼接地路径来转发调用
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
	
	/**
	 * 	http://localhost:8001/decreaseNumber
	 * 	会在协议体中携带参数
	 * @param items
	 * @return
	 */
	@GetMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
