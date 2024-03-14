package com.iemr.inventory.data.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {M_User.class})
@ExtendWith(SpringExtension.class)
class M_UserDiffblueTest {
    @Autowired
    private M_User m_User;

    /**
     * Method under test: {@link M_User#isIsSupervisor()}
     */
    @Test
    void testIsIsSupervisor() {
        // Arrange
        M_User m_User2 = new M_User();
        m_User2.setIsSupervisor(true);

        // Act and Assert
        assertTrue(m_User2.isIsSupervisor());
    }

    /**
     * Method under test: {@link M_User#isIsSupervisor()}
     */
    @Test
    void testIsIsSupervisor2() {
        // Arrange
        Timestamp dOB = mock(Timestamp.class);
        Timestamp dOJ = mock(Timestamp.class);
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        // Act and Assert
        assertTrue((new M_User(1, 1, "Jane", "Middle Name", "Doe", 1, 1, "Aadhaar No", "P AN", dOB, dOJ, 1, "janedoe",
                "iloveyou", "jane.doe@example.org", 1, "Emergency Contact Person", "Emergency Contact No", true, true,
                "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate, new ArrayList<>()))
                .isIsSupervisor());
    }

    /**
     * Method under test: {@link M_User#isIsSupervisor()}
     */
    @Test
    void testIsIsSupervisor3() {
        // Arrange
        M_User m_User2 = new M_User();
        m_User2.setIsSupervisor(false);

        // Act and Assert
        assertFalse(m_User2.isIsSupervisor());
    }

    /**
     * Method under test: {@link M_User#setIsSupervisor(boolean)}
     */
    @Test
    void testSetIsSupervisor() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new M_User()).setIsSupervisor(true);
    }

    /**
     * Method under test: {@link M_User#isDeleted()}
     */
    @Test
    void testIsDeleted() {
        // Arrange
        M_User m_User2 = new M_User();
        m_User2.setDeleted(true);

        // Act and Assert
        assertTrue(m_User2.isDeleted());
    }

    /**
     * Method under test: {@link M_User#isDeleted()}
     */
    @Test
    void testIsDeleted2() {
        // Arrange
        Timestamp dOB = mock(Timestamp.class);
        Timestamp dOJ = mock(Timestamp.class);
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        // Act and Assert
        assertTrue((new M_User(1, 1, "Jane", "Middle Name", "Doe", 1, 1, "Aadhaar No", "P AN", dOB, dOJ, 1, "janedoe",
                "iloveyou", "jane.doe@example.org", 1, "Emergency Contact Person", "Emergency Contact No", true, true,
                "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate, new ArrayList<>()))
                .isDeleted());
    }

    /**
     * Method under test: {@link M_User#isDeleted()}
     */
    @Test
    void testIsDeleted3() {
        // Arrange
        M_User m_User2 = new M_User();
        m_User2.setDeleted(false);

        // Act and Assert
        assertFalse(m_User2.isDeleted());
    }

    /**
     * Method under test: {@link M_User#setDeleted(boolean)}
     */
    @Test
    void testSetDeleted() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new M_User()).setDeleted(true);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_User#setAadhaarNo(String)}
     *   <li>{@link M_User#setCreatedBy(String)}
     *   <li>{@link M_User#setCreatedDate(Timestamp)}
     *   <li>{@link M_User#setDOB(Timestamp)}
     *   <li>{@link M_User#setDOJ(Timestamp)}
     *   <li>{@link M_User#setEmailID(String)}
     *   <li>{@link M_User#setEmergencyContactNo(String)}
     *   <li>{@link M_User#setEmergencyContactPerson(String)}
     *   <li>{@link M_User#setFirstName(String)}
     *   <li>{@link M_User#setGenderID(Integer)}
     *   <li>{@link M_User#setLastModDate(Timestamp)}
     *   <li>{@link M_User#setLastName(String)}
     *   <li>{@link M_User#setMaritalStatusID(Integer)}
     *   <li>{@link M_User#setMiddleName(String)}
     *   <li>{@link M_User#setModifiedBy(String)}
     *   <li>{@link M_User#setPAN(String)}
     *   <li>{@link M_User#setPassword(String)}
     *   <li>{@link M_User#setProviderAdminDetails(ArrayList)}
     *   <li>{@link M_User#setQualificationID(Integer)}
     *   <li>{@link M_User#setStatusID(Integer)}
     *   <li>{@link M_User#setTitleID(Integer)}
     *   <li>{@link M_User#setUserID(int)}
     *   <li>{@link M_User#setUserName(String)}
     *   <li>{@link M_User#toString()}
     *   <li>{@link M_User#getAadhaarNo()}
     *   <li>{@link M_User#getCreatedBy()}
     *   <li>{@link M_User#getCreatedDate()}
     *   <li>{@link M_User#getDOB()}
     *   <li>{@link M_User#getDOJ()}
     *   <li>{@link M_User#getEmailID()}
     *   <li>{@link M_User#getEmergencyContactNo()}
     *   <li>{@link M_User#getEmergencyContactPerson()}
     *   <li>{@link M_User#getFirstName()}
     *   <li>{@link M_User#getGenderID()}
     *   <li>{@link M_User#getLastModDate()}
     *   <li>{@link M_User#getLastName()}
     *   <li>{@link M_User#getMaritalStatusID()}
     *   <li>{@link M_User#getMiddleName()}
     *   <li>{@link M_User#getModifiedBy()}
     *   <li>{@link M_User#getPAN()}
     *   <li>{@link M_User#getPassword()}
     *   <li>{@link M_User#getProviderAdminDetails()}
     *   <li>{@link M_User#getQualificationID()}
     *   <li>{@link M_User#getStatusID()}
     *   <li>{@link M_User#getTitleID()}
     *   <li>{@link M_User#getUserID()}
     *   <li>{@link M_User#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_User m_User = new M_User();

        // Act
        m_User.setAadhaarNo("Aadhaar No");
        m_User.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        m_User.setCreatedDate(createdDate);
        Timestamp dOB = mock(Timestamp.class);
        m_User.setDOB(dOB);
        Timestamp dOJ = mock(Timestamp.class);
        m_User.setDOJ(dOJ);
        m_User.setEmailID("jane.doe@example.org");
        m_User.setEmergencyContactNo("Emergency Contact No");
        m_User.setEmergencyContactPerson("Emergency Contact Person");
        m_User.setFirstName("Jane");
        m_User.setGenderID(1);
        Timestamp lastModDate = mock(Timestamp.class);
        m_User.setLastModDate(lastModDate);
        m_User.setLastName("Doe");
        m_User.setMaritalStatusID(1);
        m_User.setMiddleName("Middle Name");
        m_User.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_User.setPAN("P AN");
        m_User.setPassword("iloveyou");
        ArrayList<Map<String, Object>> providerAdminDetails = new ArrayList<>();
        m_User.setProviderAdminDetails(providerAdminDetails);
        m_User.setQualificationID(1);
        m_User.setStatusID(1);
        m_User.setTitleID(1);
        m_User.setUserID(1);
        m_User.setUserName("janedoe");
        String actualToStringResult = m_User.toString();
        String actualAadhaarNo = m_User.getAadhaarNo();
        String actualCreatedBy = m_User.getCreatedBy();
        Timestamp actualCreatedDate = m_User.getCreatedDate();
        Timestamp actualDOB = m_User.getDOB();
        Timestamp actualDOJ = m_User.getDOJ();
        String actualEmailID = m_User.getEmailID();
        String actualEmergencyContactNo = m_User.getEmergencyContactNo();
        String actualEmergencyContactPerson = m_User.getEmergencyContactPerson();
        String actualFirstName = m_User.getFirstName();
        Integer actualGenderID = m_User.getGenderID();
        Timestamp actualLastModDate = m_User.getLastModDate();
        String actualLastName = m_User.getLastName();
        Integer actualMaritalStatusID = m_User.getMaritalStatusID();
        String actualMiddleName = m_User.getMiddleName();
        String actualModifiedBy = m_User.getModifiedBy();
        String actualPAN = m_User.getPAN();
        String actualPassword = m_User.getPassword();
        ArrayList<Map<String, Object>> actualProviderAdminDetails = m_User.getProviderAdminDetails();
        Integer actualQualificationID = m_User.getQualificationID();
        Integer actualStatusID = m_User.getStatusID();
        Integer actualTitleID = m_User.getTitleID();
        int actualUserID = m_User.getUserID();

        // Assert that nothing has changed
        assertEquals("Aadhaar No", actualAadhaarNo);
        assertEquals("Doe", actualLastName);
        assertEquals("Emergency Contact No", actualEmergencyContactNo);
        assertEquals("Emergency Contact Person", actualEmergencyContactPerson);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Jane", actualFirstName);
        assertEquals("Middle Name", actualMiddleName);
        assertEquals("P AN", actualPAN);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmailID);
        assertEquals("janedoe", m_User.getUserName());
        assertEquals("{\"userID\":1,\"Deleted\":null,\"providerAdminDetails\":[]}", actualToStringResult);
        assertEquals(1, actualUserID);
        assertEquals(1, actualGenderID.intValue());
        assertEquals(1, actualMaritalStatusID.intValue());
        assertEquals(1, actualQualificationID.intValue());
        assertEquals(1, actualStatusID.intValue());
        assertEquals(1, actualTitleID.intValue());
        assertSame(providerAdminDetails, actualProviderAdminDetails);
        assertSame(createdDate, actualCreatedDate);
        assertSame(dOB, actualDOB);
        assertSame(dOJ, actualDOJ);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: {@link M_User#M_User()}
     */
    @Test
    void testNewM_User() {
        // Arrange and Act
        M_User actualM_User = new M_User();

        // Assert
        assertNull(actualM_User.getGenderID());
        assertNull(actualM_User.getMaritalStatusID());
        assertNull(actualM_User.getQualificationID());
        assertNull(actualM_User.getStatusID());
        assertNull(actualM_User.getTitleID());
        assertNull(actualM_User.getAadhaarNo());
        assertNull(actualM_User.getCreatedBy());
        assertNull(actualM_User.getEmailID());
        assertNull(actualM_User.getEmergencyContactNo());
        assertNull(actualM_User.getEmergencyContactPerson());
        assertNull(actualM_User.getFirstName());
        assertNull(actualM_User.getLastName());
        assertNull(actualM_User.getMiddleName());
        assertNull(actualM_User.getModifiedBy());
        assertNull(actualM_User.getPAN());
        assertNull(actualM_User.getPassword());
        assertNull(actualM_User.getUserName());
        assertNull(actualM_User.getCreatedDate());
        assertNull(actualM_User.getDOB());
        assertNull(actualM_User.getDOJ());
        assertNull(actualM_User.getLastModDate());
        assertNull(actualM_User.getProviderAdminDetails());
        assertEquals(0, actualM_User.getUserID());
    }

    /**
     * Method under test:
     * {@link M_User#M_User(int, Integer, String, String, String, Integer, Integer, String, String, Timestamp, Timestamp, Integer, String, String, String, Integer, String, String, boolean, boolean, String, Timestamp, String, Timestamp, ArrayList)}
     */
    @Test
    void testNewM_User2() {
        // Arrange
        Timestamp dOB = mock(Timestamp.class);
        Timestamp dOJ = mock(Timestamp.class);
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        // Act
        M_User actualM_User = new M_User(1, 1, "Jane", "Middle Name", "Doe", 1, 1, "Aadhaar No", "P AN", dOB, dOJ, 1,
                "janedoe", "iloveyou", "jane.doe@example.org", 1, "Emergency Contact Person", "Emergency Contact No", true,
                true, "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate,
                new ArrayList<>());

        // Assert
        assertEquals("Aadhaar No", actualM_User.getAadhaarNo());
        assertEquals("Doe", actualM_User.getLastName());
        assertEquals("Emergency Contact No", actualM_User.getEmergencyContactNo());
        assertEquals("Emergency Contact Person", actualM_User.getEmergencyContactPerson());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualM_User.getCreatedBy());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualM_User.getModifiedBy());
        assertEquals("Jane", actualM_User.getFirstName());
        assertEquals("Middle Name", actualM_User.getMiddleName());
        assertEquals("P AN", actualM_User.getPAN());
        assertEquals("iloveyou", actualM_User.getPassword());
        assertEquals("jane.doe@example.org", actualM_User.getEmailID());
        assertEquals("janedoe", actualM_User.getUserName());
        assertEquals(1, actualM_User.getUserID());
        assertEquals(1, actualM_User.getGenderID().intValue());
        assertEquals(1, actualM_User.getMaritalStatusID().intValue());
        assertEquals(1, actualM_User.getQualificationID().intValue());
        assertEquals(1, actualM_User.getStatusID().intValue());
        assertEquals(1, actualM_User.getTitleID().intValue());
        assertTrue(actualM_User.getProviderAdminDetails().isEmpty());
        assertSame(createdDate, actualM_User.getCreatedDate());
        assertSame(dOB, actualM_User.getDOB());
        assertSame(dOJ, actualM_User.getDOJ());
        assertSame(lastModDate, actualM_User.getLastModDate());
    }

    /**
     * Method under test:
     * {@link M_User#M_User(int, Integer, String, String, String, Integer, Integer, String, String, Timestamp, Timestamp, Integer, String, String, String, Integer, String, String, boolean, boolean, String, Timestamp, String, Timestamp, ArrayList)}
     */
    @Test
    void testNewM_User3() {
        // Arrange
        Timestamp dOB = mock(Timestamp.class);
        Timestamp dOJ = mock(Timestamp.class);
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        ArrayList<Map<String, Object>> providerAdminDetails = new ArrayList<>();
        providerAdminDetails.add(new HashMap<>());

        // Act
        M_User actualM_User = new M_User(1, 1, "Jane", "Middle Name", "Doe", 1, 1, "Aadhaar No", "P AN", dOB, dOJ, 1,
                "janedoe", "iloveyou", "jane.doe@example.org", 1, "Emergency Contact Person", "Emergency Contact No", true,
                true, "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate,
                providerAdminDetails);

        // Assert
        assertEquals("Aadhaar No", actualM_User.getAadhaarNo());
        assertEquals("Doe", actualM_User.getLastName());
        assertEquals("Emergency Contact No", actualM_User.getEmergencyContactNo());
        assertEquals("Emergency Contact Person", actualM_User.getEmergencyContactPerson());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualM_User.getCreatedBy());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualM_User.getModifiedBy());
        assertEquals("Jane", actualM_User.getFirstName());
        assertEquals("Middle Name", actualM_User.getMiddleName());
        assertEquals("P AN", actualM_User.getPAN());
        assertEquals("iloveyou", actualM_User.getPassword());
        assertEquals("jane.doe@example.org", actualM_User.getEmailID());
        assertEquals("janedoe", actualM_User.getUserName());
        assertEquals(1, actualM_User.getUserID());
        assertEquals(1, actualM_User.getGenderID().intValue());
        assertEquals(1, actualM_User.getMaritalStatusID().intValue());
        assertEquals(1, actualM_User.getQualificationID().intValue());
        assertEquals(1, actualM_User.getStatusID().intValue());
        assertEquals(1, actualM_User.getTitleID().intValue());
        assertEquals(1, actualM_User.getProviderAdminDetails().size());
        assertSame(createdDate, actualM_User.getCreatedDate());
        assertSame(dOB, actualM_User.getDOB());
        assertSame(dOJ, actualM_User.getDOJ());
        assertSame(lastModDate, actualM_User.getLastModDate());
    }

    /**
     * Method under test:
     * {@link M_User#M_User(int, Integer, String, String, String, Integer, Integer, String, String, Timestamp, Timestamp, Integer, String, String, String, Integer, String, String, boolean, boolean, String, Timestamp, String, Timestamp, ArrayList)}
     */
    @Test
    void testNewM_User4() {
        // Arrange
        Timestamp dOB = mock(Timestamp.class);
        Timestamp dOJ = mock(Timestamp.class);
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        ArrayList<Map<String, Object>> providerAdminDetails = new ArrayList<>();
        providerAdminDetails.add(new HashMap<>());
        providerAdminDetails.add(new HashMap<>());

        // Act
        M_User actualM_User = new M_User(1, 1, "Jane", "Middle Name", "Doe", 1, 1, "Aadhaar No", "P AN", dOB, dOJ, 1,
                "janedoe", "iloveyou", "jane.doe@example.org", 1, "Emergency Contact Person", "Emergency Contact No", true,
                true, "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate,
                providerAdminDetails);

        // Assert
        assertEquals("Aadhaar No", actualM_User.getAadhaarNo());
        assertEquals("Doe", actualM_User.getLastName());
        assertEquals("Emergency Contact No", actualM_User.getEmergencyContactNo());
        assertEquals("Emergency Contact Person", actualM_User.getEmergencyContactPerson());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualM_User.getCreatedBy());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualM_User.getModifiedBy());
        assertEquals("Jane", actualM_User.getFirstName());
        assertEquals("Middle Name", actualM_User.getMiddleName());
        assertEquals("P AN", actualM_User.getPAN());
        assertEquals("iloveyou", actualM_User.getPassword());
        assertEquals("jane.doe@example.org", actualM_User.getEmailID());
        assertEquals("janedoe", actualM_User.getUserName());
        assertEquals(1, actualM_User.getUserID());
        assertEquals(1, actualM_User.getGenderID().intValue());
        assertEquals(1, actualM_User.getMaritalStatusID().intValue());
        assertEquals(1, actualM_User.getQualificationID().intValue());
        assertEquals(1, actualM_User.getStatusID().intValue());
        assertEquals(1, actualM_User.getTitleID().intValue());
        assertEquals(2, actualM_User.getProviderAdminDetails().size());
        assertSame(createdDate, actualM_User.getCreatedDate());
        assertSame(dOB, actualM_User.getDOB());
        assertSame(dOJ, actualM_User.getDOJ());
        assertSame(lastModDate, actualM_User.getLastModDate());
    }
}
