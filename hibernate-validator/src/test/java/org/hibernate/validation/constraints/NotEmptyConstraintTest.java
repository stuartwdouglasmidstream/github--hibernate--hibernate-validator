// $Id$
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,  
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validation.constraints;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

import org.hibernate.tck.annotations.SpecAssertion;

/**
 * @author Hardy Ferentschik
 */
public class NotEmptyConstraintTest {

	NotEmptyConstraint constraint;

	@Before
	public void init() {
		constraint = new NotEmptyConstraint();
	}

	@Test
	public void testIsValid() {

		assertTrue( constraint.isValid( null, null ) );
		assertTrue( constraint.isValid( "foo", null ) );
		assertTrue( constraint.isValid( "  ", null ) );

		assertFalse( constraint.isValid( "", null ) );
	}

	@Test
	@SpecAssertion(section = "2.1", note = "Incompatible type cause runtime error")
	public void testIncompatibleType() {
		try {
			constraint.isValid( new Object(), null );
			fail();
		}
		catch ( IllegalArgumentException e ) {
			// success
		}
	}
}
