package teammates.storage.entity;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import teammates.common.datatransfer.CommentSendingState;
import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.util.SanitizationHelper;

import com.google.appengine.api.datastore.Text;

/**
 * An association class that represents the association
 * Giver --> [comments about] --> FeedbackResponse.
 * Currently giver is restricted only to Instructors.
 */
@PersistenceCapable
public class FeedbackResponseComment {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private transient Long feedbackResponseCommentId;

    /** The foreign key to locate the Course object. */
    @Persistent
    private String courseId;

    /** The foreign key to locate the FeedbackSession object. */
    @Persistent
    private String feedbackSessionName;

    /** The foreign key to locate the FeedbackQuestion object. */
    @Persistent
    private String feedbackQuestionId;

    /** The course-specific email used by the giver of the comment. */
    @Persistent
    private String giverEmail;

    /** The foreign key to locate the FeedbackResponse object commented on. */
    @Persistent
    private String feedbackResponseId;

    /** Is this comment pending to be sent to recipient (through email) or sending or sent. */
    @Persistent
    private CommentSendingState sendingState;

    /* Response giver section */
    @Persistent
    private String giverSection;

    /* Response receiver section */
    @Persistent
    private String receiverSection;

    @Persistent
    private List<FeedbackParticipantType> showCommentTo;

    @Persistent
    private List<FeedbackParticipantType> showGiverNameTo;

    @Persistent
    private Boolean isVisibilityFollowingFeedbackQuestion;

    /** The creation time of this comment. */
    @Persistent
    private Date createdAt;

    /** The comment from giver about the feedback response. */
    @Persistent
    @Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
    private Text commentText;

    /** The e-mail of the account that last edited the comment. */
    @Persistent
    private String lastEditorEmail;

    /** The time in which the comment is last edited. */
    @Persistent
    private Date lastEditedAt;

    public FeedbackResponseComment(String courseId, String feedbackSessionName,
            String feedbackQuestionId, String giverEmail, String feedbackResponseId,
            CommentSendingState sendingState, Date createdAt, Text commentText,
            String giverSection, String receiverSection, List<FeedbackParticipantType> showCommentTo,
            List<FeedbackParticipantType> showGiverNameTo, String lastEditorEmail, Date lastEditedAt) {
        this.feedbackResponseCommentId = null; // Auto generated by GAE
        this.courseId = courseId;
        this.feedbackSessionName = feedbackSessionName;
        this.feedbackQuestionId = feedbackQuestionId;
        this.giverEmail = giverEmail;
        this.feedbackResponseId = feedbackResponseId;
        this.sendingState = sendingState;
        this.createdAt = createdAt;
        this.commentText = SanitizationHelper.sanitizeForRichText(commentText);
        this.giverSection = giverSection;
        this.receiverSection = receiverSection;
        this.showCommentTo = showCommentTo;
        this.showGiverNameTo = showGiverNameTo;
        this.isVisibilityFollowingFeedbackQuestion = false;
        this.lastEditorEmail = lastEditorEmail == null ? giverEmail : lastEditorEmail;
        this.lastEditedAt = lastEditedAt == null ? createdAt : lastEditedAt;
    }

    /**
     * Use only if the comment already persisted in the datastore and id generated by GAE.
     * @return the feedbackResponseCommentId
     */
    public Long getFeedbackResponseCommentId() {
        return feedbackResponseCommentId;
    }

    /* Auto generated by GAE. Don't set this.
    public void setFeedbackResponseCommentId(Long feedbackResponseCommentId) {
        this.feedbackResponseCommentId = feedbackResponseCommentId;
    }*/

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFeedbackSessionName() {
        return feedbackSessionName;
    }

    public void setFeedbackSessionName(String feedbackSessionName) {
        this.feedbackSessionName = feedbackSessionName;
    }

    public String getFeedbackQuestionId() {
        return feedbackQuestionId;
    }

    public void setFeedbackQuestionId(String feedbackQuestionId) {
        this.feedbackQuestionId = feedbackQuestionId;
    }

    public Boolean getIsVisibilityFollowingFeedbackQuestion() {
        return this.isVisibilityFollowingFeedbackQuestion;
    }

    public void setIsVisibilityFollowingFeedbackQuestion(Boolean isVisibilityFollowingFeedbackQuestion) {
        this.isVisibilityFollowingFeedbackQuestion = isVisibilityFollowingFeedbackQuestion;
    }

    public String getGiverEmail() {
        return giverEmail;
    }

    /**
     * @param giverEmail the giverEmail to set.
     *         This is the email used by the user in the course, not the one associated with the user's google account.
     */
    public void setGiverEmail(String giverEmail) {
        this.giverEmail = giverEmail;
    }

    public void setShowCommentTo(List<FeedbackParticipantType> showCommentTo) {
        this.showCommentTo = showCommentTo;
    }

    public List<FeedbackParticipantType> getShowCommentTo() {
        return showCommentTo;
    }

    public void setShowGiverNameTo(List<FeedbackParticipantType> showGiverNameTo) {
        this.showGiverNameTo = showGiverNameTo;
    }

    public List<FeedbackParticipantType> getShowGiverNameTo() {
        return showGiverNameTo;
    }

    public String getFeedbackResponseId() {
        return feedbackResponseId;
    }

    public void setFeedbackResponseId(String feedbackResponseId) {
        this.feedbackResponseId = feedbackResponseId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CommentSendingState getSendingState() {
        return sendingState;
    }

    public void setSendingState(CommentSendingState sendingState) {
        this.sendingState = sendingState;
    }

    public Text getCommentText() {
        return commentText;
    }

    public void setCommentText(Text commentText) {
        this.commentText = commentText;
    }

    public String getGiverSection() {
        return giverSection;
    }

    public void setGiverSection(String giverSection) {
        this.giverSection = giverSection;
    }

    public String getReceiverSection() {
        return receiverSection;
    }

    public void setReceiverSection(String receiverSection) {
        this.receiverSection = receiverSection;
    }

    public void setLastEditorEmail(String lastEditorEmail) {
        this.lastEditorEmail = lastEditorEmail;
    }

    public String getLastEditorEmail() {
        return this.lastEditorEmail;
    }

    public Date getLastEditedAt() {
        return this.lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
