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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Оксана
 */
@ManagedBean
@RequestScoped
public class BookkeeperTableBean {

    @EJB
    private RealPlayedRoleDAO jpaRealPlayedRoleDAO;

    @EJB
    private ContractDAO jpaContractDAO;

    @EJB
    private PerformanceDAO jpaPerformanceDAO;

    private List<Contract> contracts;
    private List<Performance> performances;
    private List<RealPlayedRole> realPlayedRoles;

    /**
     * Creates a new instance of BookkeeperViewBean
     */
    public BookkeeperTableBean() {
    }

    public List<Performance> getAllPerformances() {
        if (performances == null) {
            performances = jpaPerformanceDAO.findAll();
        }
        return performances;
    }

    public List<Contract> getAllContracts() {
        if (contracts == null) {
            contracts = jpaContractDAO.findAll();
        }
        return contracts;
    }

    public List<RealPlayedRole> getAllRealPlayedRoles() {
        if (realPlayedRoles == null) {
            realPlayedRoles = jpaRealPlayedRoleDAO.findAll();
        }
        return realPlayedRoles;
    }

    public void onContractSalaryEdit(RowEditEvent event) {
        Contract selectedContract = (Contract) event.getObject();
        jpaContractDAO.update(selectedContract);
    }
    
    public void onPerformanceBudgetEdit(RowEditEvent event) {
        Performance selectedPerformance = (Performance) event.getObject();
        jpaPerformanceDAO.update(selectedPerformance);
    }
    
    public void onPremiumEdit(RowEditEvent event) {
        RealPlayedRole role = (RealPlayedRole) event.getObject();
        jpaRealPlayedRoleDAO.update(role);
    }
}
