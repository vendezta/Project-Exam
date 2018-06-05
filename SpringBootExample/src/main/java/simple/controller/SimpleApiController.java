package simple.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import simple.model.CustomerBean;

@RestController
@RequestMapping("/simpleAPI")
public class SimpleApiController {

	@ApiOperation(value = "Response the variable name", response = String.class)
    @RequestMapping(value = "/show/name/{firstName}", method= RequestMethod.GET)
    public String showName(@PathVariable String firstName){

        return "Your name is " + firstName;
    }

    @ApiOperation(value = "Response the variable name and age ", response = String.class)
    @RequestMapping(value = "/show/name/{name}/age/{age}", method= RequestMethod.GET)
    public String showNameAge(@PathVariable("name") String name, @PathVariable("age") int age){

        return "Your name is " + name + ", you are " + age + " years old.";
    }


    @ApiOperation(value = "Response Person object ", response = CustomerBean.class)
    @RequestMapping(value = "/show/person", method= RequestMethod.PUT)
    public CustomerBean showPerson(@RequestBody CustomerBean customerBean){

    	customerBean.setFirstName("Your name is " + customerBean.getFirstName());
        return customerBean;
    }
    
}
