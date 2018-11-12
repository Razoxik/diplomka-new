<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="challenge" type="cz.upce.diplomovaprace.entity.Challenge"--%>
<%--@elvariable id="challenges" type="java.util.List<ccz.upce.diplomovaprace.entity.Challenge>"--%>

<%@ include file="common/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title ">Detail zprávy</h4>
                                <p class="card-category">Zde vidíte detail vybrané zprávy</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                Author
                                            </th>
                                            <th>
                                                Subject
                                            </th>
                                            <th>
                                                Text
                                            </th>
                                            <th>
                                                Date
                                            </th>
                                            <th>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%--<c:forEach items="${challenges}" var="challenge" varStatus="status"> --%>
                                        <tr>
                                            <td>
                                                Razox
                                            </td>
                                            <td>
                                                Pozdrav
                                            <td>
                                                Duis elementum convallis augue malesuada fermentum. Aliquam dictum,
                                                lacus eu iaculis semper, quam felis consequat diam, nec pellentesque leo
                                                urna vel dolor. Sed quis viverra sapien. Ut cursus commodo orci, ac
                                                aliquet purus. Curabitur metus purus, varius non augue nec, varius
                                                dictum ligula. Nam nisl arcu, elementum eu luctus in, pharetra a est.
                                                Morbi semper tortor quis luctus maximus. Vestibulum mattis magna vitae
                                                dapibus cursus. Nam viverra odio vitae urna aliquam, quis pellentesque
                                                elit porta. Suspendisse non tempus risus, at bibendum purus. Etiam
                                                vulputate elementum pellentesque. Nam vitae nibh malesuada, rutrum dui
                                                vel, ornare metus. Donec id velit leo. Morbi vel est quis nisl rhoncus
                                                accumsan.
                                            </td>
                                            <td class="text-primary">
                                                10. 4. 14:52
                                            </td>
                                        </tr>
                                        <%--</c:forEach>--%>

                                        </tbody>
                                    </table>
                                </div>
                                <spring:url value="reply" var="replyUrl" htmlEscape="true">
                                    <spring:param name="messageId" value="1"/>
                                </spring:url>
                                <a href="${replyUrl}" class="btn btn-primary " role="button" aria-disabled="true">Odpovědět</a>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>
<%@ include file="common/footer.jsp" %>
