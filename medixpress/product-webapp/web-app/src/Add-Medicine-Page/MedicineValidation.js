import * as yup from 'yup';

 export const MediValid=yup.object().shape({
    shopEmail:yup.string().email().required(),
    medicineName:yup.string().required(),
    medicineCatagory:yup.string().required(),
    medicineUnits:yup.number().positive().integer().required(),
    medicineDescription:yup.string().required()

})

