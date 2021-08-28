package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class speakerImpl implements SpeakerRepository {
    @Override
    public Integer saveSpeaker(Speaker speaker) {
        return 2;
    }
}

class RegisterBusinessTest {
    @Test
    @DisplayName("Invalid first name")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            registerBusiness.register(null, new Speaker());
            fail();
        } catch (ArgumentNullException e) {
            //
        }
    }

    @Test
    @DisplayName("Invalid last name")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        try {
            registerBusiness.register(null, mock);
            fail();
        } catch (ArgumentNullException e) {
            //
        }
    }

    @Test
    @DisplayName("Invalid email")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        mock.setLastName("Lastname");
        try {
            registerBusiness.register(null, mock);
            fail();
        } catch (ArgumentNullException e) {
            //
        }
    }

    @Test
    @DisplayName("Domain incorrect")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        mock.setLastName("Lastname");
        mock.setEmail("test@test.com");
        try {
            registerBusiness.register(null, mock);
            fail();
        } catch (SpeakerDoesntMeetRequirementsException e) {
            //
        }
    }

    @Test
    @DisplayName("Domain invalid")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        mock.setLastName("Lastname");
        mock.setEmail("test@test@@.com");
        try {
            registerBusiness.register(null, mock);
            fail();
        } catch (DomainEmailInvalidException e) {
            //
        }
    }

    @Test
    @DisplayName("Cannot Save")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        mock.setLastName("Lastname");
        mock.setEmail("test@gmail.com");
        mock.setExp(2);
        try {
            registerBusiness.register(null, mock);
            fail();
        } catch (SaveSpeakerException e) {
            //
        }
    }

    @Test
    @DisplayName(" Save")
    public void case07() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker mock = new Speaker();
        mock.setFirstName("FirstName");
        mock.setLastName("Lastname");
        mock.setEmail("test@gmail.com");
        mock.setExp(2);
        int actual = registerBusiness.register(new speakerImpl(), mock);
        assertEquals(2, actual);
    }

    @Test
    @DisplayName("<= 1")
    public void getFeeCaseLessThan1() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int actual = registerBusiness.getFee(1);
        assertEquals(500, actual);
    }

    @Test
    @DisplayName("<= 3")
    public void getFeeCaseLessThan3() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int actual = registerBusiness.getFee(3);
        assertEquals(250, actual);
    }

    @Test
    @DisplayName("<= 5")
    public void getFeeCaseLessThan5() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int actual = registerBusiness.getFee(5);
        assertEquals(100, actual);
    }

    @Test
    @DisplayName("<= 9")
    public void getFeeCaseLessThan9() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int actual = registerBusiness.getFee(9);
        assertEquals(50, actual);
    }

    @Test
    @DisplayName("> 9")
    public void getFeeCaseMore9() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int actual = registerBusiness.getFee(10);
        assertEquals(0, actual);
    }
}