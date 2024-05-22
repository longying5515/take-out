package com.zyt.service;

import com.zyt.dto.EmployeeDTO;
import com.zyt.dto.EmployeeLoginDTO;
import com.zyt.dto.EmployeePageQueryDTO;
import com.zyt.entity.Employee;
import com.zyt.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);
}
