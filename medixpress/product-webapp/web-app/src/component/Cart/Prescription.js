import React, { useState } from "react";
import '../../assets/stylesheets/prescription.scss'
function Prescription(props) {
    const { updateFileFromPrescription } = props
    const [file, updateFile] = useState(null)

    const onFileChange = (file) => {
        updateFile(file)
        updateFileFromPrescription(file)
    }

    const getPreviewURL = () => {
        return (URL.createObjectURL(file))
    }

    return (
        <div className="prescription-wrapper">
            <p className="heading">Upload Prescription</p>
            <p className="description">Please attach a prescription to proceed</p>
            <a className="col-xs-12 button-text  OrderViaPrescription__anchorNew___2Zust">
                <img src="https://onemg.gumlet.io/marketing/tw0dco5xexdieshf2lxs.svg" className="OrderViaPrescription__img-size___1PAey" alt="" />
                <div className="OrderViaPrescription__new-upload-box-text___21_AA">UPLOAD NEW</div>
                <input type="file" className="styles__upload-file-control___1BtZe" onChange={(e) => onFileChange(e.target.files[0])} />
            </a>
            <div className="iframe-wrapper">
                {file &&
                    <iframe className="doc-iframe" height='300px' src={getPreviewURL()} />
                }
            </div>
            
            <img alt="order-with-prescription-care-plan-img" src="https://onemg.gumlet.io/image/upload/v1625555741/xe6glhvttbkmqmbvrhle.png" className="style__image___Ny-Sa style__loaded___22epL"/>
            
           
        </div>

    )
}

export default Prescription

