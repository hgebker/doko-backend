import { formatNumber } from 'services/utils/baseUtils';

const FormattedNumberField = ({ value, className }) => {
  return (
    <p className={className} style={{ display: 'inline' }}>
      {formatNumber(value ?? 0)}
    </p>
  );
};

export default FormattedNumberField;
