package kz.epam.training.bulletin_board.command.client;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.impl.handler.*;
import kz.epam.training.bulletin_board.command.impl.form.*;

/**
 * @author Abay Assenov
 */

public enum CommandEnum {

    REGISTRATION_FORM {
        {
            this.command = new RegistrationForm();
        }
    },
    LOGIN_FORM {
        {
            this.command = new LoginForm();
        }
    },
    LOGIN {
        {
            this.command = new Login();
        }
    },
    ADVERT {
        {
            this.command = new ShowAdvertForm();
        }
    },
    REGISTRATION {
        {
            this.command = new Registration();
        }
    },
    LOGOUT {
        {
            this.command = new Logout();
        }
    },
    LOCALE {
        {
            this.command = new LocaleChange();
        }
    },
    ADD_ADVERT {
        {
            this.command = new AddAdvert();
        }
    },
    CREATE_ADVERT {
        {
            this.command = new CreateAdvertForm();
        }
    },
    DISTRICT {
        {
            this.command = new Location();
        }
    },
    USER_CABINET {
        {
            this.command = new UserCabinetForm();
        }
    },
    ADMIN_CABINET {
        {
            this.command = new AdminCabinetForm();
        }
    },
    SEARCH_ADVERT {
        {
            this.command = new SearchForm();
        }
    },
    HANDLER_ADMIN {
        {
            this.command = new AdminCabinet();
        }
    },
    HANDLER_USER {
        {
            this.command = new UserCabinet();
        }
    },
    SEARCH {
        {
            this.command = new Search();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}