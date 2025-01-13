<jsp:include page="inc/top.jsp" />
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">List</h1>
                        <div class="card mb-4">
                            <div class="card-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="/board/insert" method="post">
                                        <div class="form-group">
                                            <b><label>TITLE</label></b>
                                            <input class="form-control" type="text" name="title">
                                        </div>
                                        <div class="form-group">
                                            <b><label>CONTENT</label></b>
                                            <textarea class="form-control" rows="5" name="content"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <b><label>WRITER</label></b>
                                            <input type="text" class="form-control" name="writer">
                                        </div>
                                        <br>
                                        <button type="button" class="btn btn-outline-primary">UPDATE</button>
										<button type="button" class="btn btn-outline-secondary">RESET</button>
										<button type="button" class="btn btn-outline-success">LIST</button>
                                    </form>
                                </div>
                            
                            </div>
                        </div>
                    </div>
                </main>
<jsp:include page="inc/bottom.jsp" />