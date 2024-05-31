package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.Address;
import com.zyy.entity.RestBean;
import com.zyy.service.AddressService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/center")
public class AddressController {
    @Resource
    private AddressService addressService;

    public static class AddressListRestBean {
        public ArrayList<Address> addressList;

        public AddressListRestBean() {
        }

        public AddressListRestBean(ArrayList<Address> addressList) {
            this.addressList = addressList;
        }
    }

    public static class AddressRestBean {
        public Address newAddress;

        public AddressRestBean() {
        }

        public AddressRestBean(Address newAddress) {
            this.newAddress = newAddress;
        }
    }

    @RequestMapping("/addressList")
    public RestBean<AddressListRestBean> addressList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Dealer") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Address> addresses = addressService.getAddressList(account.getUserID());
        return RestBean.success(new AddressListRestBean(addresses));
    }

    @RequestMapping("/defaultAddress")
    public RestBean<AddressRestBean> defaultAddress(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Dealer") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        Address address = addressService.getDefaultAddress(account.getUserID());
        return RestBean.success(new AddressRestBean(Objects.requireNonNullElseGet(address, Address::new)));
    }

    @RequestMapping("/addAddress")
    public RestBean<AddressRestBean> addAddress(@RequestBody Address address, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Dealer") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        Address newAddress = addressService.createNewAddress(address, account.getUserID());
        if (newAddress == null)
            return RestBean.failure(400, "Operation failure");
        return RestBean.success(new AddressRestBean(newAddress));
    }

    public static class RequestParam1 {
        public Integer addressID;

        public RequestParam1() {
        }
    }

    @RequestMapping("/delAddress")
    public RestBean<String> delAddress(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Integer addressID = requestParam1.addressID;
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Dealer") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (addressService.deleteAddress(addressID))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/editAddress")
    public RestBean<AddressRestBean> editAddress(@RequestBody Address address, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Dealer") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        Address address1 = addressService.editAddress(address, account.getUserID());
        if (address1 != null)
            return RestBean.success(new AddressRestBean(address1));
        return RestBean.failure(400, "Operation failure");
    }
}
