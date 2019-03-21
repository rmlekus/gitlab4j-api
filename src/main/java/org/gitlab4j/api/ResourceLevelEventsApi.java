package org.gitlab4j.api;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.ResourceLabelEvent;

/**
 * This class implements the client side API for the GitLab resource label events calls.
 */
public class ResourceLevelEventsApi extends AbstractApi {

    public ResourceLevelEventsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }
 
    /**
     * Gets a list of all resource label events for a single issue of a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code></pre>
     *
     * @param id The ID or URL-encoded path of the project
     * @param issue_iid The IID of an issue
     * @return a list of all label events for a single issue of a project.
     * @throws GitLabApiException if any exception occurs
     */
    public List<ResourceLabelEvent> getProjectIssueLabelEvents(String id, int issue_iid) throws GitLabApiException {
        return (getProjectIssueLabelEvents(id, issue_iid, getDefaultPerPage()).all());
    }

    /**
     * Gets a list of all resource label events for a single issue of a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code></pre>
     *
     * @param id The ID or URL-encoded path of the project
     * @param issue_iid The IID of an issue
     * @param perPage the number of ResourceLabelEvent per page
     * @return a list of ResourceLabelEvent for the specified project and issue
     * @throws GitLabApiException if any exception occurs
     */
    public List<ResourceLabelEvent> getProjectIssueLabelEvents(String id, int issue_iid, int page, int perPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("id", id)
                .withParam("issue_iid", issue_iid)
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(),
                "projects", id, "issues", issue_iid, "resource_label_events");
        return (response.readEntity(new GenericType<List<ResourceLabelEvent>>() {}));
    }

    /**
     * Gets a list of all resource label events for a single issue of a project in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code></pre>
     *
     * @param id The ID or URL-encoded path of the project
     * @param issue_iid The IID of an issue
     * @param itemsPerPage the number of Event instances that will be fetched per page
     * @return a Pager of events for the specified user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ResourceLabelEvent> getProjectIssueLabelEvents(String id, int issue_iid, int itemsPerPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
        		.withParam("id", id)
                .withParam("issue_iid", issue_iid);

        return (new Pager<ResourceLabelEvent>(this, ResourceLabelEvent.class, itemsPerPage, formData.asMap(),
        		"projects", id, "issues", issue_iid, "resource_label_events"));
    }

    /**
     * Gets a Stream of all resource label events for a single issue of a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code></pre>
     *
     * @param id The ID or URL-encoded path of the project
     * @param issue_iid The IID of an issue
     * @return a Stream of ResourceLabelEvent a single issue of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<ResourceLabelEvent> getProjectIssueLabelEventsStream(String id, int issue_iid) throws GitLabApiException {
        return (getProjectIssueLabelEvents(id, issue_iid, getDefaultPerPage()).stream());
    }

}
