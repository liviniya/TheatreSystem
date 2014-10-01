/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao_interfaces.ContractDAO;
import dao_interfaces.PerformanceDAO;
import dao_interfaces.RealPlayedRoleDAO;
import entities.Contract;
import entities.Performance;
import entities.RealPlayedRole;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Оксана
 */
@ManagedBean
@RequestScoped
public class BookkeeperViewBean {
    @EJB
    private RealPlayedRoleDAO jpaRealPlayedRoleDAO;
    
    @EJB
    private ContractDAO jpaContractDAO;

    @EJB
    private PerformanceDAO jpaPerformanceDAO;

    /**
     * Creates a new instance of BookkeeperViewBean
     */
    public BookkeeperViewBean() {
    }

    public List<Performance> getAllPerformances() {
        return jpaPerformanceDAO.findAll();
    }
    
    public List<Contract> getAllContracts() {
        return jpaContractDAO.findAll();
    }
    
    public List<RealPlayedRole> getAllRealPlayedRoles() {
        return jpaRealPlayedRoleDAO.findAll();
    }
}
