/*************************************************************************
 * Copyright 2014 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you
 * need additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights
 *   Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *    http://aws.amazon.com/apache2.0
 *
 *   or in the "license" file accompanying this file. This file is
 *   distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *   ANY KIND, either express or implied. See the License for the specific
 *   language governing permissions and limitations under the License.
 ************************************************************************/
package com.eucalyptus.simpleworkflow.common.model;

import java.io.Serializable;

/**
 * <p>
 * Provides details of the <code>CompleteWorkflowExecutionFailed</code>
 * event.
 * </p>
 */
public class CompleteWorkflowExecutionFailedEventAttributes implements Serializable {

    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     */
    private String cause;

    /**
     * The id of the <code>DecisionTaskCompleted</code> event corresponding
     * to the decision task that resulted in the
     * <code>CompleteWorkflowExecution</code> decision to complete this
     * execution. This information can be useful for diagnosing problems by
     * tracing back the cause of events.
     */
    private Long decisionTaskCompletedEventId;

    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     *
     * @return The cause of the failure. This information is generated by the system
     *         and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     *         set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     *         sufficient permissions. For details and example IAM policies, see <a
     *         href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     *         IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     *
     * @see CompleteWorkflowExecutionFailedCause
     */
    public String getCause() {
        return cause;
    }
    
    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     *
     * @param cause The cause of the failure. This information is generated by the system
     *         and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     *         set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     *         sufficient permissions. For details and example IAM policies, see <a
     *         href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     *         IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     *
     * @see CompleteWorkflowExecutionFailedCause
     */
    public void setCause(String cause) {
        this.cause = cause;
    }
    
    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     *
     * @param cause The cause of the failure. This information is generated by the system
     *         and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     *         set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     *         sufficient permissions. For details and example IAM policies, see <a
     *         href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     *         IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     *
     * @see CompleteWorkflowExecutionFailedCause
     */
    public CompleteWorkflowExecutionFailedEventAttributes withCause(String cause) {
        this.cause = cause;
        return this;
    }

    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     *
     * @param cause The cause of the failure. This information is generated by the system
     *         and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     *         set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     *         sufficient permissions. For details and example IAM policies, see <a
     *         href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     *         IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     *
     * @see CompleteWorkflowExecutionFailedCause
     */
    public void setCause(CompleteWorkflowExecutionFailedCause cause) {
        this.cause = cause.toString();
    }
    
    /**
     * The cause of the failure. This information is generated by the system
     * and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     * set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     * sufficient permissions. For details and example IAM policies, see <a
     * href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     * IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>UNHANDLED_DECISION, OPERATION_NOT_PERMITTED
     *
     * @param cause The cause of the failure. This information is generated by the system
     *         and can be useful for diagnostic purposes. <note>If <b>cause</b> is
     *         set to OPERATION_NOT_PERMITTED, the decision failed because it lacked
     *         sufficient permissions. For details and example IAM policies, see <a
     *         href="http://docs.aws.amazon.com/amazonswf/latest/developerguide/swf-dev-iam.html">Using
     *         IAM to Manage Access to Amazon SWF Workflows</a>.</note>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     *
     * @see CompleteWorkflowExecutionFailedCause
     */
    public CompleteWorkflowExecutionFailedEventAttributes withCause(CompleteWorkflowExecutionFailedCause cause) {
        this.cause = cause.toString();
        return this;
    }

    /**
     * The id of the <code>DecisionTaskCompleted</code> event corresponding
     * to the decision task that resulted in the
     * <code>CompleteWorkflowExecution</code> decision to complete this
     * execution. This information can be useful for diagnosing problems by
     * tracing back the cause of events.
     *
     * @return The id of the <code>DecisionTaskCompleted</code> event corresponding
     *         to the decision task that resulted in the
     *         <code>CompleteWorkflowExecution</code> decision to complete this
     *         execution. This information can be useful for diagnosing problems by
     *         tracing back the cause of events.
     */
    public Long getDecisionTaskCompletedEventId() {
        return decisionTaskCompletedEventId;
    }
    
    /**
     * The id of the <code>DecisionTaskCompleted</code> event corresponding
     * to the decision task that resulted in the
     * <code>CompleteWorkflowExecution</code> decision to complete this
     * execution. This information can be useful for diagnosing problems by
     * tracing back the cause of events.
     *
     * @param decisionTaskCompletedEventId The id of the <code>DecisionTaskCompleted</code> event corresponding
     *         to the decision task that resulted in the
     *         <code>CompleteWorkflowExecution</code> decision to complete this
     *         execution. This information can be useful for diagnosing problems by
     *         tracing back the cause of events.
     */
    public void setDecisionTaskCompletedEventId(Long decisionTaskCompletedEventId) {
        this.decisionTaskCompletedEventId = decisionTaskCompletedEventId;
    }
    
    /**
     * The id of the <code>DecisionTaskCompleted</code> event corresponding
     * to the decision task that resulted in the
     * <code>CompleteWorkflowExecution</code> decision to complete this
     * execution. This information can be useful for diagnosing problems by
     * tracing back the cause of events.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param decisionTaskCompletedEventId The id of the <code>DecisionTaskCompleted</code> event corresponding
     *         to the decision task that resulted in the
     *         <code>CompleteWorkflowExecution</code> decision to complete this
     *         execution. This information can be useful for diagnosing problems by
     *         tracing back the cause of events.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public CompleteWorkflowExecutionFailedEventAttributes withDecisionTaskCompletedEventId(Long decisionTaskCompletedEventId) {
        this.decisionTaskCompletedEventId = decisionTaskCompletedEventId;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCause() != null) sb.append("Cause: " + getCause() + ",");
        if (getDecisionTaskCompletedEventId() != null) sb.append("DecisionTaskCompletedEventId: " + getDecisionTaskCompletedEventId() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getCause() == null) ? 0 : getCause().hashCode()); 
        hashCode = prime * hashCode + ((getDecisionTaskCompletedEventId() == null) ? 0 : getDecisionTaskCompletedEventId().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof CompleteWorkflowExecutionFailedEventAttributes == false) return false;
        CompleteWorkflowExecutionFailedEventAttributes other = (CompleteWorkflowExecutionFailedEventAttributes)obj;
        
        if (other.getCause() == null ^ this.getCause() == null) return false;
        if (other.getCause() != null && other.getCause().equals(this.getCause()) == false) return false; 
        if (other.getDecisionTaskCompletedEventId() == null ^ this.getDecisionTaskCompletedEventId() == null) return false;
        if (other.getDecisionTaskCompletedEventId() != null && other.getDecisionTaskCompletedEventId().equals(this.getDecisionTaskCompletedEventId()) == false) return false; 
        return true;
    }
    
}
    