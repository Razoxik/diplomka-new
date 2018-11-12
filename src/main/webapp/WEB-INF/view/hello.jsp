<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="message" type="cz.upce.diplomovaprace.entity.Message"--%>
<%--@elvariable id="messages" type="java.util.List<ccz.upce.diplomovaprace.entity.Message>"--%>

<%@ include file="common/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">Mailbox</h4>
                                <p class="card-category">Here are your messages</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <form name='loginForm'
                                          action="<c:url value='/login' />" method='POST'>

                                        <div class="form-group">
                                            <label class="label-control">User name</label>
                                            <input type='text' name='userName'value="razox"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="label-control">Password</label>
                                            <input type='password' name='password'  value="razox"/>
                                        </div>


                                        <button type="submit" class="btn btn-primary">Submit</button>
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
<%@ include file="common/footer.jsp" %>
