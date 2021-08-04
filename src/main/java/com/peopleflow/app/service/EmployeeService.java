package com.peopleflow.app.service;

import com.peopleflow.app.domain.Employee;
import com.peopleflow.app.domain.enumeration.State;
import com.peopleflow.app.repository.EmployeeRepository;
import com.peopleflow.app.service.dto.EmployeeDTO;
import com.peopleflow.app.service.mapper.EmployeeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
public class EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    /**
     * Partially update a employee.
     *
     * @param employeeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmployeeDTO> partialUpdate(EmployeeDTO employeeDTO) {
        log.debug("Request to partially update Employee : {}", employeeDTO);

        return employeeRepository
            .findById(employeeDTO.getId())
            .map(
                existingEmployee -> {
                    employeeMapper.partialUpdate(existingEmployee, employeeDTO);
                    return existingEmployee;
                }
            )
            .map(employeeRepository::save)
            .map(employeeMapper::toDto);
    }

    /**
     * State update a employee.
     *
     * @param id the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmployeeDTO> stateUpdate(Long id, State state) {
        log.debug("Request to State update Employee : {}", id);

        return employeeRepository
            .findById(id)
            .map(
                existingEmployee -> {
                    existingEmployee.setState(state);
                    return existingEmployee;
                }
            )
            .map(employeeRepository::save)
            .map(employeeMapper::toDto);
    }

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }
}
