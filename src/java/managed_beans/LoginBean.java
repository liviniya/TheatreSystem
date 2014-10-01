/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao_interfaces.WorkerDAO;
import entities.Worker;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utils.PasswordEncryptor;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private WorkerDAO jpaWorkerDAO;   

    private String login;
    private String password;
    private Worker worker;
    private String name;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String tryLogin() { 
        password = PasswordEncryptor.getEncoded(password);
        worker = jpaWorkerDAO.findByLoginPassword(login, password);
        if (worker == null) { 
            FacesContext context = FacesContext.getCurrentInstance();            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    context.getApplication().getResourceBundle(context, "msg")
                            .getString("loginPasswordIncorrect"), null));
            return "index";
        }
        name = worker.getName();
        switch (worker.getPosition()) {
            case Secretary:                
                return "secretary_workers?faces-redirect=true";
            case Producer:
                return "producer_performances?faces-redirect=true";
            case Bookkeeper:
                return "bookkeeper_performances?faces-redirect=true";
            case Director:
                return "director_performances?faces-redirect=true";
        }
        return null;
    }
    
    public String logout() {
        login = null;
        password = null;
        worker = null;
        return "index?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
