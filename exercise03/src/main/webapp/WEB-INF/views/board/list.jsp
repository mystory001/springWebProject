<jsp:include page="inc/top.jsp" />
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">List</h1>
                        <div class="card mb-4">
                            <div class="card-body">
                            	<a href="/board/insert"><button type="button" class="btn btn-outline-dark" style="float: right;">insert</button></a>
                                <table class="table">
								  <thead class="thead-light">
								    <tr>
								      <th scope="col">#BNO</th>
								      <th scope="col">TITLE</th>
								      <th scope="col">CONTENT</th>
								      <th scope="col">WRITER</th>
								      <th scope="col">DATE</th>
								      <th scope="col">UPDATEDATE</th>
								    </tr>
								  </thead>
								  <tbody>
								    <tr>
								      <th scope="row">1</th>
								      <td>Mark</td>
								      <td>Otto</td>
								      <td>@mdo</td>
								      <td></td>
								      <td></td>
								      <td></td>
								    </tr>
								  </tbody>
								</table>
							<nav aria-label="Page navigation example">
							  <ul class="pagination justify-content-end">
							    <li class="page-item disabled">
							      <a class="page-link" href="#" tabindex="-1">Previous</a>
							    </li>
							    <li class="page-item"><a class="page-link" href="#">1</a></li>
							    <li class="page-item">
							      <a class="page-link" href="#">Next</a>
							    </li>
							  </ul>
							</nav>
                            </div>
                        </div>
                    </div>
                </main>
<jsp:include page="inc/bottom.jsp" />