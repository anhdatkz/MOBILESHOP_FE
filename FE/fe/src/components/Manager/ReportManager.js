import style from "./Manager.module.css"

function ReportManager() {
    return (
        <>
            <div className={style["manager"]}>
                <div className="report">
                    <div className="report-header d-flex justify-content-between">
                        <h2 className="title">Thông kê doanh thu</h2>
                    </div>
                    <div className="report-content d-flex justify-content-between align-items-center mt-2">
                        <div>
                            <label className="fw-bold me-3">Từ</label>
                            <input type="month" id="start" min="2018-03" defaultValue="2018-05"/>
                        </div>
                        <div>
                            <label className="fw-bold me-3">Đến</label>
                            <input type="month" id="end"/>
                        </div>
                        <button className="btn btn-primary me-5">Bắt đầu</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ReportManager