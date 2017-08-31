/*
* [y] hybris Platform
*
* Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
*
* This software is the confidential and proprietary information of SAP
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with SAP.
*
*/

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.client.DefaultEntitlementRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class for exception messages testing
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-rest-client-test-spring.xml")
public class RestClientExceptionMessagesTest
{

    @Autowired
    private DefaultEntitlementRestClient defaultEntitlementRestClient;

    @Test
    public void testObjectNotFoundException()
    {
        final String badID = "no_such_id";
        try
        {
            defaultEntitlementRestClient.getGrant(badID);
            fail();
        }
        catch (ObjectNotFoundException e)
        {
            assertEquals("The object with id = [" + badID + "] doesn't exist.", e.getMessage());
        }
    }

}
