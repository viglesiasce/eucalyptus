/*************************************************************************
 * Copyright 2009-2014 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 ************************************************************************/
package com.eucalyptus.simpleworkflow;

import static com.eucalyptus.simpleworkflow.common.SimpleWorkflowMetadata.WorkflowExecutionMetadata;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import com.eucalyptus.component.ComponentIds;
import com.eucalyptus.entities.UserMetadata;
import com.eucalyptus.simpleworkflow.common.SimpleWorkflow;
import com.eucalyptus.simpleworkflow.common.SimpleWorkflowMetadatas;
import com.eucalyptus.simpleworkflow.common.model.WorkflowEventAttributes;
import com.eucalyptus.util.FullName;
import com.eucalyptus.util.OwnerFullName;
import com.google.common.collect.Lists;

/**
 *
 */
@Entity
@PersistenceContext( name = "eucalyptus_simpleworkflow" )
@Table( name = "swf_workflow_execution" )
@Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
public class WorkflowExecution extends UserMetadata<WorkflowExecution.ExecutionStatus> implements WorkflowExecutionMetadata {
  private static final long serialVersionUID = 1L;

  public enum ExecutionStatus {
    Open,
    Closed,
    ;

    public String toString( ) {
      return name( ).toUpperCase( );
    }
  }

  public enum CloseStatus {
    Completed,
    Failed,
    Canceled,
    Terminated,
    Continued_As_New,
    Timed_Out
    ;

    public String toString( ) {
      return name( ).toUpperCase( );
    }
  }

  public enum DecisionStatus {
    Idle,
    Pending,
    Active,
    ;
  }

  @ManyToOne
  @JoinColumn( name = "domain_id", nullable = false, updatable = false )
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  private Domain domain;

  @ManyToOne
  @JoinColumn( name = "workflow_type_id", nullable = false, updatable = false )
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  private WorkflowType workflowType;

  @Column( name = "workflow_id", nullable = false, updatable = false )
  private String workflowId;

  @Column( name = "child_policy", nullable = false, updatable = false )
  private String childPolicy;

  @Column( name = "domain", nullable = false, updatable = false )
  private String domainName;

  @Column( name = "task_list", nullable = false, updatable = false )
  private String taskList;

  @Column( name = "exec_start_to_close_timeout", nullable = false, updatable = false )
  private Integer executionStartToCloseTimeout;

  @Column( name = "task_start_to_close_timeout", nullable = false, updatable = false )
  private Integer taskStartToCloseTimeout;

  @Column( name = "cancel_requested" )
  private Boolean cancelRequested;

  @Column( name = "decision_status" )
  @Enumerated( EnumType.STRING )
  private DecisionStatus decisionStatus;

  @Column( name = "close_status" )
  @Enumerated( EnumType.STRING )
  private CloseStatus closeStatus;

  @Column( name = "close_timestamp" )
  @Temporal( TemporalType.TIMESTAMP )
  private Date closeTimestamp;

  @ElementCollection
  @CollectionTable( name = "swf_workflow_execution_tags" )
  @JoinColumn( name = "workflow_execution_id" )
  @OrderColumn( name = "tag_index")
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  private List<String> tagList;

  @Column( name = "latest_activity_timestamp" )
  @Temporal( TemporalType.TIMESTAMP )
  private Date latestActivityTaskScheduled;

  @Column( name = "latest_execution_context" )
  @Lob
  @Type(type="org.hibernate.type.StringClobType")
  private String latestExecutionContext;

  @OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true, mappedBy = "workflowExecution" )
  @OrderColumn( name = "event_id" )
  private List<WorkflowHistoryEvent> workflowHistory;

  protected WorkflowExecution( ) {
  }

  protected WorkflowExecution( final OwnerFullName owner, final String displayName ) {
    super( owner, displayName );
  }

  public static WorkflowExecution create( final OwnerFullName owner,
                                          final String name, /* runId */
                                          final Domain domain,
                                          final WorkflowType workflowType,
                                          final String workflowId,
                                          @Nullable final String childPolicy,
                                          @Nullable final String taskList,
                                          @Nullable final Integer executionStartToCloseTimeout,
                                          @Nullable final Integer taskStartToCloseTimeout,
                                          final List<String> tags,
                                          final List<WorkflowEventAttributes> eventAttributes ) {
    final WorkflowExecution workflowExecution = new WorkflowExecution( owner, name );
    workflowExecution.setDomain( domain );
    workflowExecution.setDomainName( domain.getDisplayName( ) );
    workflowExecution.setWorkflowType( workflowType );
    workflowExecution.setWorkflowId( workflowId );
    workflowExecution.setState( ExecutionStatus.Open );
    workflowExecution.setChildPolicy( childPolicy == null ?
        workflowType.getDefaultChildPolicy() :
        childPolicy );
    workflowExecution.setTaskList( taskList == null ?
        workflowType.getDefaultTaskList() :
        taskList );
    workflowExecution.setExecutionStartToCloseTimeout( executionStartToCloseTimeout == null ?
        workflowType.getDefaultExecutionStartToCloseTimeout() :
        executionStartToCloseTimeout );
    workflowExecution.setTaskStartToCloseTimeout( taskStartToCloseTimeout == null ?
        workflowType.getDefaultTaskStartToCloseTimeout() :
        taskStartToCloseTimeout );
    workflowExecution.setTagList( tags );
    workflowExecution.setDecisionStatus( DecisionStatus.Pending );
    workflowExecution.setWorkflowHistory( Lists.<WorkflowHistoryEvent>newArrayList( ) );
    for ( final WorkflowEventAttributes attributes : eventAttributes ) {
      workflowExecution.addHistoryEvent( WorkflowHistoryEvent.create( workflowExecution, attributes ) );
    }
    return workflowExecution;
  }

  public static WorkflowExecution exampleWithOwner( final OwnerFullName owner ) {
    return new WorkflowExecution( owner, null );
  }

  public static WorkflowExecution exampleWithName( final OwnerFullName owner, final String name ) {
    return new WorkflowExecution( owner, name );
  }

  public static WorkflowExecution exampleWithPendingDecision( final OwnerFullName owner,
                                                              final String domain,
                                                              final String taskList ) {
    final WorkflowExecution workflowExecution = new WorkflowExecution( owner, null );
    workflowExecution.setDomainName( domain );
    workflowExecution.setTaskList( taskList );
    workflowExecution.setDecisionStatus( DecisionStatus.Pending );
    return workflowExecution;
  }

  public static WorkflowExecution exampleWithUniqueName( final OwnerFullName owner,
                                                         final String domain,
                                                         final String runId ) {
    final WorkflowExecution workflowExecution = new WorkflowExecution( owner, runId );
    workflowExecution.setUniqueName( createUniqueName( owner.getAccountNumber(), domain, runId ) );
    return workflowExecution;
  }

  public static WorkflowExecution exampleForOpenWorkflow( final OwnerFullName owner,
                                                          final String workflowId ) {
    final WorkflowExecution workflowExecution = new WorkflowExecution( owner, null );
    workflowExecution.setWorkflowId( workflowId );
    workflowExecution.setState( ExecutionStatus.Open );
    return workflowExecution;
  }

  @Override
  protected String createUniqueName( ) {
    return createUniqueName( getOwnerAccountNumber(),
        SimpleWorkflowMetadatas.toDisplayName().apply( getDomain() ),
        getDisplayName() );
  }

  private static String createUniqueName( final String accountNumber,
                                          final String domain,
                                          final String runId ) {
    return accountNumber + ":" + domain + ":" + runId;
  }

  public Long addHistoryEvent( final WorkflowHistoryEvent event ) {
    // Order would be filled in on save, but we may need the event
    // identifier before the entity is stored
    event.setEventOrder( (long) workflowHistory.size( ) );
    workflowHistory.add( event );
    return event.getEventId();
  }

  @Override
  public String getPartition( ) {
    return "eucalyptus";
  }

  @Override
  public FullName getFullName( ) {
    return FullName.create.vendor( "euca" )
        .region( ComponentIds.lookup( SimpleWorkflow.class ).name() )
        .namespace( this.getOwnerAccountNumber() )
        .relativeId(
            "domain", SimpleWorkflowMetadatas.toDisplayName( ).apply( getDomain() ),
            "run-id", getDisplayName() );
  }

  public Domain getDomain() {
    return domain;
  }

  public void setDomain( final Domain domain ) {
    this.domain = domain;
  }

  public WorkflowType getWorkflowType( ) {
    return workflowType;
  }

  public void setWorkflowType( final WorkflowType workflowType ) {
    this.workflowType = workflowType;
  }

  public String getWorkflowId( ) {
    return workflowId;
  }

  public void setWorkflowId( final String workflowId ) {
    this.workflowId = workflowId;
  }

  public String getChildPolicy( ) {
    return childPolicy;
  }

  public void setChildPolicy( final String childPolicy ) {
    this.childPolicy = childPolicy;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName( final String domainName ) {
    this.domainName = domainName;
  }

  public String getTaskList( ) {
    return taskList;
  }

  public void setTaskList( final String taskList ) {
    this.taskList = taskList;
  }

  public Integer getExecutionStartToCloseTimeout( ) {
    return executionStartToCloseTimeout;
  }

  public void setExecutionStartToCloseTimeout( final Integer executionStartToCloseTimeout ) {
    this.executionStartToCloseTimeout = executionStartToCloseTimeout;
  }

  public Integer getTaskStartToCloseTimeout( ) {
    return taskStartToCloseTimeout;
  }

  public void setTaskStartToCloseTimeout( final Integer taskStartToCloseTimeout ) {
    this.taskStartToCloseTimeout = taskStartToCloseTimeout;
  }

  public Boolean getCancelRequested( ) {
    return cancelRequested;
  }

  public void setCancelRequested( final Boolean cancelRequested ) {
    this.cancelRequested = cancelRequested;
  }

  public DecisionStatus getDecisionStatus( ) {
    return decisionStatus;
  }

  public void setDecisionStatus( final DecisionStatus decisionStatus ) {
    this.decisionStatus = decisionStatus;
  }

  public CloseStatus getCloseStatus( ) {
    return closeStatus;
  }

  public void setCloseStatus( final CloseStatus closeStatus ) {
    this.closeStatus = closeStatus;
  }

  public Date getCloseTimestamp( ) {
    return closeTimestamp;
  }

  public void setCloseTimestamp( final Date closeTimestamp ) {
    this.closeTimestamp = closeTimestamp;
  }

  public List<String> getTagList( ) {
    return tagList;
  }

  public void setTagList( final List<String> tagList ) {
    this.tagList = tagList;
  }

  public Date getLatestActivityTaskScheduled( ) {
    return latestActivityTaskScheduled;
  }

  public void setLatestActivityTaskScheduled( final Date latestActivityTaskScheduled ) {
    this.latestActivityTaskScheduled = latestActivityTaskScheduled;
  }

  public String getLatestExecutionContext( ) {
    return latestExecutionContext;
  }

  public void setLatestExecutionContext( final String latestExecutionContext ) {
    this.latestExecutionContext = latestExecutionContext;
  }

  public List<WorkflowHistoryEvent> getWorkflowHistory() {
    return workflowHistory;
  }

  public void setWorkflowHistory( final List<WorkflowHistoryEvent> workflowHistory ) {
    this.workflowHistory = workflowHistory;
  }
}
